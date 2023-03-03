import { Edge, Node, Position } from 'reactflow';

import { GraphResource, NodeResource } from '@/api';

export function getEdges(graph: GraphResource): Edge[] {
	const edges: Edge[] = [];

	for (const node of graph.nodes || []) {
		for (const childId of node.childrenInternalIds || []) {
			edges.push({
				id: `${node.internalId}-${childId}`,
				source: String(node.internalId),
				target: String(childId),
			});
		}
	}

	return edges;
}

type NodeByInternalId = Record<
	NonNullable<NodeResource['internalId']>,
	NodeResource
>;

const getNodeByInternalIdData = (
	graphNodes: NodeResource[]
): NodeByInternalId =>
	graphNodes.reduce<NodeByInternalId>((acc, node) => {
		acc[node.internalId || 0] = node;

		return acc;
	}, {});

const getStartNode = (graphNodes: NodeResource[]): NodeResource | null => {
	for (const node of graphNodes) {
		if (node.positionType === 'START') {
			return node;
		}
	}

	return null;
};

export function getNodes(graph: GraphResource): Node[] {
	const resultNodes: Node[] = [];

	const graphNodes = graph.nodes || [];
	const levelByNodeId: Record<number, number> = {};

	const nodeByInternalId: NodeByInternalId =
		getNodeByInternalIdData(graphNodes);

	const startNode = getStartNode(graphNodes);

	if (!startNode) {
		return resultNodes;
	}

	type InCount = Record<NonNullable<NodeResource['internalId']>, number>;
	const inCount: InCount = {};

	for (const node of graphNodes) {
		for (const childId of node.childrenInternalIds || []) {
			inCount[childId] = (inCount[childId] || 0) + 1;
		}
	}

	const bfsQueue: NodeResource['internalId'][] = [startNode.internalId];
	levelByNodeId[startNode.internalId || 0] = 0;

	const nodeIdsByLevel: Record<
		number,
		NonNullable<NodeResource['internalId']>[]
	> = {};
	nodeIdsByLevel[0] = [startNode.internalId || 0];

	let maxLevelCount = 1;

	while (bfsQueue.length !== 0) {
		const currentNodeId = bfsQueue.pop();
		const currentNode = nodeByInternalId[currentNodeId || 0];

		for (const nextNodeId of currentNode.childrenInternalIds || []) {
			--inCount[nextNodeId];

			if (!(nextNodeId in levelByNodeId) && inCount[nextNodeId] === 0) {
				bfsQueue.push(nextNodeId);
				levelByNodeId[nextNodeId] = levelByNodeId[currentNodeId || 0] + 1;

				nodeIdsByLevel[levelByNodeId[nextNodeId]] =
					nodeIdsByLevel[levelByNodeId[nextNodeId]] || [];
				nodeIdsByLevel[levelByNodeId[nextNodeId]].push(nextNodeId);

				maxLevelCount = Math.max(
					maxLevelCount,
					nodeIdsByLevel[levelByNodeId[nextNodeId]].length
				);
			}
		}
	}

	const nodeWidth = 100;
	const nodeHeight = 100;
	const levelsGap = 150;
	const nodeGap = 200;
	const maxLevelHeight =
		maxLevelCount * nodeHeight + (maxLevelCount + 1) * nodeGap;

	let currentLevelX = 0;

	for (const level in nodeIdsByLevel) {
		const nodesCount = nodeIdsByLevel[level].length;
		const gap = (maxLevelHeight - nodesCount * nodeWidth) / (nodesCount + 1);

		let currentY = gap;
		for (const nodeId of nodeIdsByLevel[level]) {
			resultNodes.push({
				id: String(nodeId),
				position: {
					x: currentLevelX,
					y: currentY,
				},
				data: { label: nodeId },
				sourcePosition: Position.Right,
				targetPosition: Position.Left,
				className: 'node',
			});

			currentY += nodeHeight + gap;
		}

		currentLevelX += levelsGap;
	}

	return resultNodes;
}

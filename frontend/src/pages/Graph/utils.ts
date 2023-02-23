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

export function getNodes(graph: GraphResource): Node[] {
	const resultNodes: Node[] = [];

	const graphNodes = graph.nodes || [];
	const levelByNodeId: Record<number, number> = {};

	type NodeByInternalId = Record<
		NonNullable<NodeResource['internalId']>,
		NodeResource
	>;
	const nodeByInternalId: NodeByInternalId =
		graphNodes.reduce<NodeByInternalId>((acc, node) => {
			acc[node.internalId || 0] = node;

			return acc;
		}, {});

	let startNode: NodeResource | null = null;

	for (const node of graphNodes) {
		if (node.positionType === 'START') {
			startNode = node;
		}
	}

	if (!startNode) {
		return resultNodes;
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
			if (!(nextNodeId in levelByNodeId)) {
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
	const levelsGap = 200;
	const nodeGap = 50;
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

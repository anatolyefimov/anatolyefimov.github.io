import React, { useCallback, useEffect } from 'react';

import { useParams } from 'react-router-dom';
import {
	addEdge,
	Background,
	Controls,
	MiniMap,
	OnConnect,
	ReactFlow,
	useEdgesState,
	useNodesState,
} from 'reactflow';

import { graphsApi } from '@/api';
import { getEdges, getNodes } from '@/pages/Graph/utils';
import { newItemId } from '@/router';

import './Graph.css';

// const initialNodes: Node[] = [
// 	{
// 		id: '1',
// 		position: { x: 0, y: 0 },
// 		data: { label: '1' },
// 		sourcePosition: Position.Right,
// 		targetPosition: Position.Left,
// 		className: 'node',
// 	},
// 	{
// 		id: '2',
// 		position: { x: 300, y: 0 },
// 		data: { label: '2' },
// 		sourcePosition: Position.Right,
// 		targetPosition: Position.Left,
// 		className: 'node',
// 	},
// ];

// const initialEdges = [{ id: 'e1-2', source: '1', target: '2' }];

const Graph: React.FC = () => {
	const { id } = useParams();

	const [nodes, setNodes, onNodesChange] = useNodesState([]);
	const [edges, setEdges, onEdgesChange] = useEdgesState([]);

	const onConnect: OnConnect = useCallback(
		(params) => setEdges((eds) => addEdge(params, eds)),
		[setEdges]
	);

	useEffect(() => {
		async function getData() {
			const { data } = await graphsApi.getGraph(Number(id));

			return data;
		}

		getData().then((data) => {
			const graphData = data.content;

			if (graphData) {
				const newNodes = getNodes(graphData);
				const newEdges = getEdges(graphData);

				setNodes(newNodes);
				setEdges(newEdges);
			}
		});
	}, [id, setEdges, setNodes]);

	if (id === newItemId) {
		return <>New graph is Coming soon...</>;
	}

	return (
		<main className="main">
			<ReactFlow
				nodes={nodes}
				edges={edges}
				onNodesChange={onNodesChange}
				onEdgesChange={onEdgesChange}
				onConnect={onConnect}
				fitView
			>
				<MiniMap />
				<Controls />
				<Background />
			</ReactFlow>
		</main>
	);
};

export default Graph;

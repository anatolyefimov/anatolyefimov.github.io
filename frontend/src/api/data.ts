import { GraphResource } from '@/api/generated';

export const graph: { content: GraphResource } = {
	content: {
		id: 6,
		name: 'graph-special-for-anatoly',
		nodes: [
			{
				id: 24,
				internalId: 2,
				name: '2',
				positionType: 'INTERMEDIATE',
				nodeType: 'STORAGE',
				connectionSection: {
					interactionMode: 'READ',
					transformationBefore: {
						context: { jslt: 'test' },
						payload: { jslt: 'test' },
						headers: { jslt: 'test' },
					},
					connectionId: 1,
				},
				childrenInternalIds: [5, 4],
			},
			{
				id: 26,
				internalId: 1,
				name: '1',
				positionType: 'START',
				nodeType: 'STORAGE',
				connectionSection: {
					interactionMode: 'READ',

					connectionId: 1,
				},
				childrenInternalIds: [2, 3, 4],
			},
			{
				id: 25,
				internalId: 3,
				name: '3',
				positionType: 'INTERMEDIATE',
				nodeType: 'STORAGE',

				childrenInternalIds: [4],
			},
			{
				id: 21,
				internalId: 6,
				name: '6',
				positionType: 'TERMINAL',
				nodeType: 'STORAGE',
				childrenInternalIds: [],
			},
			{
				id: 23,
				internalId: 5,
				name: '5',
				positionType: 'INTERMEDIATE',
				nodeType: 'STORAGE',
				childrenInternalIds: [4],
			},
			{
				id: 22,
				internalId: 4,
				name: '4',
				positionType: 'INTERMEDIATE',
				nodeType: 'STORAGE',
				childrenInternalIds: [6],
			},
		],
	},
};

import React from 'react';

import { createBrowserRouter, Navigate, RouteObject } from 'react-router-dom';

import App from '@/App';
import GraphsList from '@/pages/GraphsList';

export const paths = {
	index: '/',
	graphs: '/graphs',
	graphWithId: '/graphs/:id',
};

export const newItemId = 'new';

export const routes: RouteObject[] = [
	{
		path: paths.index,
		element: <App />,
		children: [
			{
				path: paths.graphs,
				element: <GraphsList />,
			},

			{ path: '/', element: <Navigate to={paths.graphs} /> },
		],
	},
	{ path: '*', element: <Navigate to={paths.graphs} /> },
];

export const router = createBrowserRouter(routes);

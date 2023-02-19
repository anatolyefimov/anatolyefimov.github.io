import React from 'react';

import { createBrowserRouter, Navigate, RouteObject } from 'react-router-dom';

import App from '@/App';
import GraphsList from '@/pages/GraphsList';

const baseUrl = import.meta.env.BASE_URL;

export const paths = {
	index: baseUrl,
	graphs: `${baseUrl}graphs`,
	graphWithId: `${baseUrl}graphs/:id`,
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

			{ path: paths.index, element: <Navigate to={paths.graphs} /> },
		],
	},
	{ path: '*', element: <Navigate to={paths.graphs} /> },
];

export const router = createBrowserRouter(routes);

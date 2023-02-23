import { Configuration, GraphControllerApi } from './generated';

const configuration = new Configuration({
	basePath:
		import.meta.env.MODE === 'production'
			? import.meta.env.VITE_API_BASE_PATH
			: 'http://localhost:5173/api',
});

export const graphsApi = new GraphControllerApi(configuration);

export * from './generated';

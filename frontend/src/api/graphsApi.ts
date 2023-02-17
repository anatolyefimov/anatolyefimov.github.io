import { Graph } from '@/api/types';

import { axios } from './axios';

const baseUrl = '/gateways';

export const getList = async () => {
	return axios.get<Graph[]>(baseUrl);
};

export const getItem = async (id: string) => {
	return axios.get<Graph>(`${baseUrl}/${id}`);
};

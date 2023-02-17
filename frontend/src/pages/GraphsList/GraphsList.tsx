import React, { useCallback, useEffect, useMemo, useState } from 'react';

import { Link } from 'react-router-dom';
import EditIcon from '@mui/icons-material/Edit';
import { IconButton } from '@mui/material';
import { DataGrid, GridColumns, GridRowIdGetter } from '@mui/x-data-grid';

import { graphsApi } from '@/api';
import { Graph } from '@/api/types';
import { paths } from '@/router';

import GridToolbar from './GridToolbar';

const GatewaysList: React.FC = () => {
	const [isLoading, setIsLoading] = useState<boolean>(true);
	const [graphs, setGraphs] = useState<Graph[]>([]);

	useEffect(() => {
		async function getData() {
			const { data } = await graphsApi.getList();

			setGraphs(data);
		}

		getData()
			.catch((error) => {
				console.error(error);
			})
			.finally(() => setIsLoading(false));
	}, []);

	const getRowId: GridRowIdGetter<Graph> = useCallback(({ id }) => id, []);

	const columns: GridColumns<Graph> = useMemo(
		() => [
			{
				field: 'id',
				headerName: 'ID',
				sortable: false,
				width: 150,
			},
			{ field: 'name', headerName: 'Name', sortable: false, flex: 1 },
			{
				field: 'actions',
				type: 'actions',
				getActions: (params) => [
					<IconButton
						component={Link}
						to={paths.graphWithId.replace(':id', String(params.id))}
						key="edit"
					>
						<EditIcon />
					</IconButton>,
				],
			},
		],
		[]
	);

	return (
		<main className="main">
			<DataGrid<Graph>
				columns={columns}
				rows={graphs}
				getRowId={getRowId}
				getRowHeight={() => 'auto'}
				disableColumnMenu
				disableSelectionOnClick
				sx={{
					'& .MuiDataGrid-cellContent': {
						overflow: 'hidden',
						textOverflow: 'ellipsis',
						whiteSpace: 'nowrap',
					},
				}}
				loading={isLoading}
				components={{
					Toolbar: GridToolbar,
				}}
			/>
		</main>
	);
};

export default React.memo(GatewaysList);

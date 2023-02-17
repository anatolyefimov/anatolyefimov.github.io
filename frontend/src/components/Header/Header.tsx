import React from 'react';

import { Link } from 'react-router-dom';
import { Box, Tab, Tabs } from '@mui/material';

import { paths } from '@/router';

const Header: React.FC = () => {
	// const routeMatch = useRouteMatch();

	const currentTab = paths.graphs;

	return (
		<header data-testid="header">
			<Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
				<Tabs centered value={currentTab}>
					<Tab
						label="Graphs"
						value={paths.graphs}
						to={paths.graphs}
						component={Link}
					/>
				</Tabs>
			</Box>
		</header>
	);
};

export default Header;

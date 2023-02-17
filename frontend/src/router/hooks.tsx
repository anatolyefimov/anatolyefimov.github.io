import { matchPath, useLocation } from 'react-router-dom';

import { paths } from './';

export const useRouteMatch = () => {
	const { pathname } = useLocation();

	const patterns = Object.values(paths);

	for (const pattern of patterns) {
		const possibleMatch = matchPath(pattern, pathname);

		if (possibleMatch) {
			console.log(possibleMatch);
			return possibleMatch;
		}
	}

	return null;
};

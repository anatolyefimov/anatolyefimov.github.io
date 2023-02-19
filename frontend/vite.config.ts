import { defineConfig, UserConfigExport } from 'vite';
import eslint from 'vite-plugin-eslint';
import react from '@vitejs/plugin-react';

const path = require('path');

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
	const config: UserConfigExport = {
		resolve: {
			alias: {
				'@/': path.join(__dirname, 'src/'),
				'@pkg/types/': path.join(__dirname, '../../packages/types'),
			},
		},
		plugins: [react(), eslint()],
	};

	if (mode === 'production') {
		config.base = '/node-pipes/';
	}

	return config;
});

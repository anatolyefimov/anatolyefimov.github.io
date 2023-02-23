import { defineConfig, loadEnv, UserConfigExport } from 'vite';
import eslint from 'vite-plugin-eslint';
import react from '@vitejs/plugin-react';

const path = require('path');

// https://vitejs.dev/config/
export default defineConfig(({ mode }) => {
	process.env = { ...process.env, ...loadEnv(mode, process.cwd()) };

	const config: UserConfigExport = {
		resolve: {
			alias: {
				'@/': path.join(__dirname, 'src/'),
			},
		},
		plugins: [react(), eslint({ lintOnStart: true })],
		server: {
			proxy: {
				'/api': {
					target: process.env.VITE_API_BASE_PATH,
					changeOrigin: true,
					secure: false,
					rewrite: (url) => url.replace(/^\/api/, ''),
				},
			},
		},
	};

	if (mode === 'production') {
		config.base = '/node-pipes/';
	}

	return config;
});

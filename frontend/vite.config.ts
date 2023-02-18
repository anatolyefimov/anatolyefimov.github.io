import { defineConfig } from 'vite';
import eslint from 'vite-plugin-eslint';
import react from '@vitejs/plugin-react';

const path = require('path');

// https://vitejs.dev/config/
export default defineConfig({
	resolve: {
		alias: {
			'@/': path.join(__dirname, 'src/'),
			'@pkg/types/': path.join(__dirname, '../../packages/types'),
		},
	},
	plugins: [react(), eslint()],
	base: '/node-pipes/',
});

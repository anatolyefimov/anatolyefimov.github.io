export type Node = {
	id: number;
	children: Node['id'];
};

export type Graph = {
	id: number;
	nodes: Node[];
	name: string;
};

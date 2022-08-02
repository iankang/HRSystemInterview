export interface Role {
    id: number;
    name: string;
}

export interface User {
    id: number;
    username: string;
    email: string;
    roles: Role[];
}

export interface Assessment {
    createdAt: Date;
    updatedAt: Date;
    id: number;
    users: User[];
    isCompleted?: any;
    questionsAnswered: number;
    totalNumberOfQuestions: number;
}
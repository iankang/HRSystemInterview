

  export class Role {
    id: number;
    name: string;
    constructor(id:number, name:string) {
      this.id = id;
      this.name = name;
    }
  }

  export class UserProfile {
    bio?: string;
    connectedChatId?: string;
    createdAt?: string;
    email?: string;
    fixedUsername?: string;
    followersCount?: number;
    followingCount?: number;
    handle?: string;
    lunnaId?: string;
    profileUrl?: string;
    wallpaperUrl?: string;
    token?: string;
  }

  export class UserDev {
    username?: string;
    email?: string;
    password?: string;
    roles?: Role[];
    isEmailVerified?: boolean;
    isActive?: boolean;
    id?: number;
    devProfPicUrl?: any;
    userProfile?: UserProfile;
  }

  export class User {
    access_token?: string;
    token_type?: string;
    user?: UserDev;
  }


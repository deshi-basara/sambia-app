import production from '../config/production';
import development from '../config/development';

let config;
switch (process.env.NODE_ENV) {
    case 'production':
        config = production;
        break;
    case 'development':
    default:
        config = development;
        break;
}

export default config;

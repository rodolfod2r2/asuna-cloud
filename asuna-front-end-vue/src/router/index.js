import {createRouter, createWebHistory} from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import DataJpa from '../components/pages/data/jpa/Jpa.vue'
import DataMongoDB from '../components/pages/data/mongo/db/MongoDB.vue'
import DataMongoReactive from '../components/pages/data/mongo/reactive/MongoReactive.vue'
import DataMongoRouter from '../components/pages/data/mongo/route/MongoRouter.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HelloWorld,
        meta: {
            breadcrumb: [
                {name: 'Home', link: '/'}
            ]
        }
    },
    {
        path: '/data-jpa',
        name: 'data-jpa',
        component: DataJpa,
        meta: {
            breadcrumb: [
                {name: 'Home', link: '/'},
                {name: 'Message With Data JPA', link: 'data-jpa'}
            ]
        }
    },
    {
        path: '/data-mongo',
        name: 'data-mongo',
        component: DataMongoDB,
        meta: {
            breadcrumb: [
                {name: 'Home', link: '/'},
                {name: 'Message With Data Mongo DB', link: 'data-mongo'}
            ]
        }
    },
    {
        path: '/data-mongo-reactive',
        name: 'data-mongo-reactive',
        component: DataMongoReactive,
        meta: {
            breadcrumb: [
                {name: 'Home', link: '/'},
                {name: 'Message With Data Mongo Reactive DB', link: 'data-mongo-reactive'}
            ]
        }
    },
    {
        path: '/data-mongo-route',
        name: 'data-mongo-route',
        component: DataMongoRouter,
        meta: {
            breadcrumb: [
                {name: 'Home', link: '/'},
                {name: 'Message With Data Mongo Reactive DB and Router API', link: 'data-mongo-route'}
            ]
        }
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
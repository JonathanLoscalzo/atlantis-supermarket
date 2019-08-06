import { applyMiddleware, combineReducers, compose, createStore } from 'redux';
import thunk from 'redux-thunk';
import { connectRouter } from 'connected-react-router'
import { routerMiddleware } from 'connected-react-router'
// @ts-ignore
import { reducer as formReducer } from 'redux-form'
// @ts-ignore
import { persistStore, persistReducer } from 'redux-persist'
import storage from 'redux-persist/lib/storage' // defaults to localStorage for web
// @ts-ignore
import { composeWithDevTools } from 'redux-devtools-extension';

import client from '../modules/clients';
import auth from '../modules/auth'
import product from '../modules/products'
import home from '../modules/home'
import provider from '../modules/providers'
import category from '../modules/products/admin/categories'
import batch from '../modules/products/admin/batches'
import cart from '../modules/cart/index';
import sales from '../modules/sales/index'


export default function configureStore(history, initialState) {
    const reducers = {
        form: formReducer,
        router: connectRouter(history),
        auth,
        product,
        home,
        client,
        provider,
        category,
        batch,
        cart,
        sales
        // client,
        // element, 
        // task,
        // repair
    };

    const middleware = [
        thunk,
        routerMiddleware(history)
    ];

    const enhancers = [];
    const isDevelopment = process.env.NODE_ENV === 'development';

    // @ts-ignore
    if (isDevelopment && typeof window !== 'undefined' && window.devToolsExtension) {
        // @ts-ignore
        enhancers.push(window.devToolsExtension());
    }

    const rootReducer = combineReducers({
        ...reducers
    });


    // @ts-ignore
    const persistConfig = {
        key: 'root',
        storage,
    }

    // const persistedReducer = persistReducer(persistConfig, rootReducer)
    let persistedReducer = rootReducer;
    let store = createStore(
        persistedReducer,
        initialState,
        compose(applyMiddleware(...middleware), ...enhancers)
    );

    let persistor = persistStore(store)
    return { store, persistor };
}
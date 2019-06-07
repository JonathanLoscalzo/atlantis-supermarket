import React from 'react';
import { Route, Switch } from 'react-router';
import Layout from '../../components/Layout';
//import Home from './components/Home';
import { ToastContainer } from 'react-toastify';

import ClientPage from '../clients/list/container/Page';
// import ClientEdit from './modules/clients/container/ClientEdit';
// import ClientNew from './modules/clients/container/ClientNew';
// import ClientView from './modules/clients/container/ClientView';
import HomePage from '../home/container/HomePage';
import LoginPage from '../auth/containers/LoginPage';
import ProductPage from '../products/admin/list/containers/ProductListPage'
import LogoutPage from '../auth/containers/LogoutPage';
// import ElementListPage from './modules/element/list/container/ElementListPage'
// import TaskListPage from './modules/task/list/container/TaskListPage'
// import RepairListPage from './modules/repair/list/container/RepairListPage';
import ProviderPage from '../providers/list/container/Page'
import { isAdmin } from '../../common/auth'


const Private = (props) => {
    if (localStorage.getItem('JWT_LOGIN')) {
        return (<React.Fragment> {props.children} </React.Fragment>)
    } else {
        return (<React.Fragment><LoginPage /></React.Fragment>)
    }
}

const renderAdminRoutes = () => {
    if (isAdmin()) {
        return (
            <React.Fragment>
                <Route path="/provider" component={ProviderPage} />
            </React.Fragment>
        )
    }
}

export default (props) => (
    <div>
        <Private>
            <Layout {...props} >
                <Switch>
                    <Route exact path="/" component={HomePage} />
                    <Route path="/product" component={ProductPage} />
                    <Route exact path="/clients" component={ClientPage} />
                    {renderAdminRoutes()}

                    {/* <Route exact path='/' component={Home} />
                <Route exact path='/client' component={Clients} />
                <Route path='/client/edit/:id' component={ClientEdit} />
                <Route path='/client/show/:id' component={ClientView} />
                <Route path='/client/new' component={ClientNew} />
                <Route path='/element' component={ElementListPage} />
                <Route path='/repair' component={RepairListPage} />
                <Route path='/task' component={TaskListPage} />*/}
                    <Route path="/logout" component={LogoutPage} />
                    <Route component={NoMatch} />
                </Switch>
            </Layout>
            <ToastContainer autoClose={2000} />
        </Private>
    </div>
);

const NoMatch = ({ location }) => (
    <div>
        <h3>No match for <code>{location.pathname}</code></h3>
    </div>
)
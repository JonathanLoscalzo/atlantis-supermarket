import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Switch, Route } from 'react-router-dom'


import { getClients } from '../index'
import Presentation from '../presentation/Presentation'
import View from '../../view/container/Page'
import Spinner from '../../../../components/loading/spinner'

class Page extends React.Component {
    componentWillMount(props) {
        this.props.getClients();
    }

    render() {

        let urls = {
            // new: `${this.props.match.url}/new`,
            // edit: `${this.props.match.url}/edit/:id`,
            // remove: `${this.props.match.url}/remove/:id`,
            view: `${this.props.match.url}/view/:id`
        }

        return (
            <Spinner loading={this.props.loading}>
                <Switch>
                    {/* <Route path={urls.new} component={ProductCreate} /> */}
                    {/* <Route path={urls.edit} component={ProductUpdateView} /> */}
                    <Route path={urls.view} component={View} />
                    <Presentation clients={this.props.clients} />
                </Switch>
            </Spinner>
        )
    }
}

const mapStateToProps = (state) => {
    return ({
        loading: state.client.list.loading,
        clients: state.client.list.clients
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ getClients }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(Page)



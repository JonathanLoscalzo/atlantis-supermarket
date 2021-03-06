import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import RemovePage from '../../remove/container/ElementRemovePage'
import EditPage from '../../update/container/ElementUpdatePage'
import NewPage from '../../create/container/CreatePage'

import { getProviders, onPageSizeChange, removeElementAt, goToCreate } from '../index'
import { Switch, Route } from 'react-router-dom'
import Presentation from '../presentation/Presentation'

import { Spinner, validator } from '../../../../../shared'

const df = () => (<div></div>)
class ProvidersPage extends React.Component {

    componentDidMount() {
        //this.props.getProviders()
    }

    componentDidUpdate(prevProps, prevState, snapshot) {

        if (this.props.location.removed) {
            
            let id = this.props.location.removed
            delete this.props.location.removed
            this.props.removeElementAt(id)
        }
    }

    render() {
        let urls = {
            new: `${this.props.match.url}/new`,
            edit: `${this.props.match.url}/edit/:id`,
            remove: `${this.props.match.url}/remove/:id`,
            view: `${this.props.match.url}/view/:id`
        }

        return (
            <Spinner loading={this.props.loading}>
                <Switch>
                    <Route path={urls.new} component={NewPage} />
                    <Route path={urls.edit} component={EditPage} />
                    <Presentation
                        fetchData={this.props.getProviders}
                        urls={urls}
                        {...this.props} />
                </Switch>
                <Route path={urls.remove} component={RemovePage} />
                <Route path={urls.view} component={df} />
            </Spinner>

        )
    }
}

const mapStateToProps = ({ category: element }) => {
    return ({
        loading: element.list.loading,
        defaultPageSize: element.list.defaultPageSize,
        data: element.list.data.rows,
        pages: element.list.data.pages,
        data_loading: element.list.data.loading,
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ getProviders, onPageSizeChange, removeElementAt, goToCreate }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProvidersPage)



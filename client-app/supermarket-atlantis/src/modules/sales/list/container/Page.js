import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';


import { getSales, onPageSizeChange, removeElementAt } from '../index'
import { Switch, Route } from 'react-router-dom'
import Presentation from '../presentation/Presentation'

import { Spinner, validator } from '../../../shared'

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
                    <Presentation
                        fetchData={this.props.getSales}
                        urls={urls}
                        {...this.props} />
                </Switch>
                <Route path={urls.view} component={df} />
            </Spinner>

        )
    }
}

const mapStateToProps = ({ sales: entity }) => {
    return ({
        loading: entity.list.loading,
        defaultPageSize: entity.list.defaultPageSize,
        data: entity.list.data.rows,
        pages: entity.list.data.pages,
        data_loading: entity.list.data.loading,
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ getSales, onPageSizeChange, removeElementAt }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProvidersPage)



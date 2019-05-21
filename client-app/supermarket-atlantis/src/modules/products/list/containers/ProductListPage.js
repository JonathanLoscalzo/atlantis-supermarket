import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { get_products } from '../index'
import { Switch, Route } from 'react-router-dom'

import Spinner from '../../../../components/loading/spinner'

const df = () => (<div></div>)

class ProductListPage extends React.Component {

    render() {
        const { list } = this.props;
        console.log(this.props.match.url)
        return (
            // <div class="col-md-12">
            //     <div class="card">
            //         <div class="card-header">
            //             <h5 class="title">Productos</h5>
            //         </div>
            //         <div class="card-body">
            //             aloha
            //         </div>
            //     </div>
            // </div>
            <Spinner loading={this.props.loading}>
                <Switch>
                    <Route path={`${this.props.match.url}/new`} component={df} />
                    <Route path={`${this.props.match.url}/edit/:id`} component={df} />
                    {/* <ElementList {...this.props} /> */}
                </Switch>
                <Route path={`${this.props.match.url}/remove/:id`} component={df} />
                <Route path={`${this.props.match.url}/view/:id`} component={df} />
            </Spinner>
        )
    }
}

const mapStateToProps = (state) => {
    return ({
        list: []
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ get_products }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)



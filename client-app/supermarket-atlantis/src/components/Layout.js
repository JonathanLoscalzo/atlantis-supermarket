import React from 'react';
import { Col, Container, Row } from 'reactstrap';
import Sidebar from './page/Sidebar';
import NavMenu from './page/NavMenu';
import Footer from './page/Footer';
import BodyContainer from './page/BodyContainer';
import { connect } from 'react-redux';
import { isAdmin } from '../common/auth'

const Layout = props => (
    <React.Fragment>
        <NavMenu />
        <div className="container-fluid">
            <div className="row mt-2">
                {/* { isAdmin() && <Sidebar menu={props.menu} {...props} /> } */}
                <Sidebar menu={props.menu} {...props} /> 
                <BodyContainer {...props} />
            </div>
            {/* <Footer /> */}
        </div>
    </React.Fragment >
);

const mapStateToProps = state => ({
    router: state.router
})

export default connect(mapStateToProps)(Layout)
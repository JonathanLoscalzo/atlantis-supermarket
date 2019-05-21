import React from 'react';
import { Col, Container, Row } from 'reactstrap';
import Sidebar from './page/Sidebar';
import NavMenu from './page/NavMenu';
import Footer from './page/Footer';
import BodyContainer from './page/BodyContainer';
import { connect } from 'react-redux';

const Layout =  props => (
    <React.Fragment>
        {/* //<NavMenu /> */}
        <div>
            <Sidebar menu={props.menu} {...props} />
            <div className="main-panel">
                <NavMenu />
                <BodyContainer {...props} />
            </div>
            <Footer />
        </div>
    </React.Fragment >
);
const mapStateToProps = state => ({
    router: state.router
})

export default connect(mapStateToProps)(Layout)
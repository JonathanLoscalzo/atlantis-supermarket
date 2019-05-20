import React from 'react';
import { Col, Container, Row } from 'reactstrap';
import Sidebar from './page/Sidebar';
import NavMenu from './page/NavMenu';
import Footer from './page/Footer';
import BodyContainer from './page/BodyContainer';


export default props => (
    <React.Fragment>
        {/* //<NavMenu /> */}
        <div>
            <Sidebar />
            <div className="main-panel">
                <NavMenu />
                <BodyContainer {...props} />
            </div>
            <Footer />
        </div>
    </React.Fragment >
);
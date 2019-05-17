import React from 'react';
import { Col, Container, Row } from 'reactstrap';
//import NavMenu from './NavMenu';

export default props => (
    <React.Fragment>
        {/* //<NavMenu /> */}
        <Container fluid>
            {props.children}
        </Container>
    </React.Fragment>
);
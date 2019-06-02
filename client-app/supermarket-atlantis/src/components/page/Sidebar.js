import React from 'react'
import logo from './supermarket-logo.png'
import { NavLink, Link } from 'react-router-dom'
import { Col, Button, UncontrolledCollapse, CardHeader, Card, CardBody, Collapse } from 'reactstrap'

export default class Sidebar extends React.Component {


    render() {
        let { menu, router } = this.props;

        return (
            <aside className="col-md-2">
                <section className="row">
                    {renderMenu(menu, router)}
                </section>
            </aside>
        )
    }
}

const renderMenu = (menu, router) => {
    return (
        <div className="row">

            {/* <div className="logo">
                    <a href="http://www.creative-tim.com" className="simple-text logo-normal">
                        <img src={logo} alt="logo" />
                        Atlantis
                    </a>
                </div> */}

            <div className="" id="sidebar-wrapper">
                <ul className="nav">
                    {menu.map(m => {
                        if (m.children.length == 0) {
                            return (
                                <li key={m.title} className={router.location.pathname == m.to ? "active" : ""}>
                                    <NavLink to={m.to}>
                                        <i class={m.icon}></i>
                                        <p>{m.title}</p>
                                    </NavLink>
                                </li>
                            )
                        }
                        else {
                            return (
                                <MenuWithChildren key={m.title} m={m} router={router} />
                            )
                        }
                    })}
                </ul>
            </div>
        </div>
    )
}

const MenuWithChildren = ({ m: menu, router }) => {
    return (
        <Col>
            <Card>
                <CardHeader>
                    <h5>
                        <Button id={menu.title + "-toggler"} color="link">
                            {menu.title}
                        </Button>
                    </h5>
                </CardHeader>
                <UncontrolledCollapse toggler={"#" + menu.title + "-toggler"}>
                    <CardBody>
                        {
                            menu.children.map((m, i) => (
                                <li key={m.title + "-" + i} className={router.location.pathname == m.to ? "active" : ""}>
                                    <NavLink key={m.title} to={m.to}>
                                        <i className={m.icon}></i>
                                        <p>{m.title}</p>
                                    </NavLink>
                                </li>))
                        }
                    </CardBody>
                </UncontrolledCollapse>
            </Card>
        </Col>
    )
};
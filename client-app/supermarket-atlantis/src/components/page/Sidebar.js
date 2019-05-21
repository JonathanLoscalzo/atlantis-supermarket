import React from 'react'
import logo from './supermarket-logo.png'
import { NavLink, Link } from 'react-router-dom'
import { Button, UncontrolledCollapse, Card, Car} from 'reactstrap'

export default class Sidebar extends React.Component {


    render() {
        let { menu, router } = this.props;

        return (
            <div class="sidebar" data-color="blue">

                <div class="logo">
                    <a href="http://www.creative-tim.com" class="simple-text logo-normal">
                        <img src={logo} alt="logo" />
                        Atlantis
                     </a>
                </div>

                <div class="sidebar-wrapper" id="sidebar-wrapper">
                    <ul class="nav">
                        {menu.map(m => (
                            <li className={router.location.pathname == m.to ? "active" : ""}>
                                <NavLink to={m.to}>
                                    <i class={m.icon}></i>
                                    <p>{m.title}</p>
                                </NavLink>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        )
    }
}
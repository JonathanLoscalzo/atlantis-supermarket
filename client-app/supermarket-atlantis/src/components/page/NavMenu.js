import React from 'react'
import { NavLink } from 'react-router-dom'

export default class NavMenu extends React.Component {
    render() {

        return (
                <nav class="navbar navbar-expand-lg navbar-transparent  bg-primary  navbar-absolute">
                    <div class="container-fluid">
                        <div class="navbar-translate">
                            <a class="navbar-brand" href="/presentation.html">Atlantis</a>
                        </div>

                        <div class="collapse navbar-collapse justify-content-end">
                            <ul class="navbar-nav">

                                <li class="nav-item dropdown">
                                    <a
                                        className="nav-link dropdown-toggle"
                                        id="navbarDropdownMenuLink"
                                        data-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false">
                                        <i class="now-ui-icons users_single-02"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                                        <NavLink className="dropdown-item" to="/product"> Editar </NavLink>
                                        <a class="dropdown-item" href="#">Editar</a>
                                        <a class="dropdown-item" href="#">Otras</a>
                                    </div>
                                </li>
                            </ul>

                        </div>
                    </div>
                </nav>
        )
    }
}
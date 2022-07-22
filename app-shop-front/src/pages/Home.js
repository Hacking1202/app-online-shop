import React from 'react';
import {
    Collapse,
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    NavItem,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';
import "./css/Home.css";
import "./js/HomeStyle";
import "./../img/Phone.png";
import 'react-toastify/dist/ReactToastify.css';
import "./Category";
import {getCategory} from "../redux/actions/AppAction";
import {connect} from "react-redux";

class Home extends React.Component {
    componentDidMount() {
        this.props.dispatch(getCategory())
    }

    constructor(props) {
        super(props);


        this.toggle = this.toggle.bind(this);
        this.state = {
            isOpen: false
        };
    }


    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });


    }

    render() {
        const {categorys} = this.props;

        const myFunction = () => {
            var input, filter, ul, li, a, i;
            input = document.getElementById("mySearch");
            filter = input.value.toUpperCase();
            ul = document.getElementById("myMenu");
            li = ul.getElementsByTagName("li");
            for (i = 0; i < li.length; i++) {
                a = li[i].getElementsByTagName("a")[0];
                if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    li[i].style.display = "";
                } else {
                    li[i].style.display = "none";
                }
            }
        }
        return (

            <div>
                <Navbar color="danger" dark={true} expand="md" className="navbar">
                    <NavbarBrand href="/" style={{marginLeft: "90px"}}>Ketmon</NavbarBrand>
                    <NavbarToggler onClick={this.toggle}/>
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav className="ml-auto" navbar>
                            <NavItem>
                                <NavLink href="/category/" style={{marginLeft: "90px"}}>Category</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="https://t.me/Men_Kimman">Telegram</NavLink>
                            </NavItem>
                            <UncontrolledDropdown nav inNavbar>
                                <DropdownToggle nav caret>
                                    Options
                                </DropdownToggle>
                                <DropdownMenu right>
                                    <DropdownItem>
                                        Option 1
                                    </DropdownItem>
                                    <DropdownItem>
                                        Option 2
                                    </DropdownItem>
                                    <DropdownItem divider/>
                                    <DropdownItem>
                                        Reset
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                        </Nav>
                    </Collapse>

                </Navbar>
                <h1 style={{textAlign: "center", padding: "15px"}}>Ketmon.uz Ishonichli Qulay Va Tez Yetkazib Berish
                    Xizmati</h1>
                <br/>

                <div className="input-group" style={{marginLeft: "330px"}}>
                    <div className="form-outline">
                        <input type="search" id="mySearch" className="form-control" onKeyUp={myFunction}
                               style={{width: "900px"}}/>
                        <label className="form-label" htmlFor="form1">Search</label>
                    </div>
                </div>

                <ul id="myMenu">
                    <li><a href="#">HTML</a></li>
                    <li><a href="#">CSS</a></li>
                    <li><a href="#">JavaScript</a></li>
                    <li><a href="#">PHP</a></li>
                    <li><a href="#">Python</a></li>
                    <li><a href="#">jQuery</a></li>
                    <li><a href="#">SQL</a></li>
                    <li><a href="#">Bootstrap</a></li>
                    <li><a href="#">Node.js</a></li>
                </ul>

                <div className="cards container" style={{justifyContent: "center"}}>
                    {categorys.length != null ?
                        categorys.map((item, i) =>
                            <li className="card">
                                <img src={require('./../img/Phone.png')}
                                     style={{width: "100px", marginLeft: "50px", marginTop: "25px"}}/>
                                <h4 style={{marginLeft: "15px", marginTop: "15px"}}>{item.name}</h4>
                            </li>
                        )
                        : "Malumot mavjud emas"
                    }
                </div>

                <footer className="text-center footers text-lg-start bg-light text-muted">
                    <div className="footers">
                        <section
                            className="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
                            <div className="me-5 d-none d-lg-block">
                                <span>Get connected with us on social networks:</span>
                            </div>
                        </section>

                        <section>
                            <div className="text-center text-md-start mt-5">
                                <div className="row mt-3">
                                    <div className="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                                        <h6 className="text-uppercase fw-bold mb-4">
                                            <i className="fas fa-gem me-3"></i>ITCA TEAM
                                        </h6>
                                        <p>
                                            ITCA Team Top Reliable Qualitative And Faster Team For Really Projects</p>
                                    </div>

                                    <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">

                                        <h6 className="text-uppercase fw-bold mb-4">
                                            Products
                                        </h6>
                                        <p>
                                            <a href="#!" className="text-reset">Angular</a>
                                        </p>
                                        <p>
                                            <a href="#!" className="text-reset">React</a>
                                        </p>
                                        <p>
                                            <a href="#!" className="text-reset">Vue</a>
                                        </p>
                                        <p>
                                            <a href="#!" className="text-reset">Laravel</a>
                                        </p>
                                    </div>

                                    <div className="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">

                                        <h6 className="text-uppercase fw-bold mb-4">
                                            Useful links
                                        </h6>
                                        <p>
                                            <a href="#!" className="text-reset">Pricing</a></p>
                                        <p>
                                            <a href="#!" className="text-reset">Settings</a>
                                        </p>
                                        <p>
                                            <a href="#!" className="text-reset">Orders</a>
                                        </p>
                                        <p>
                                            <a href="#!" className="text-reset">Help</a>
                                        </p></div>

                                    <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">

                                        <h6 className="text-uppercase fw-bold mb-4">
                                            Contact
                                        </h6>
                                        <p><i className="fas fa-home me-3"></i> New York, NY 10012, US</p>
                                        <p>
                                            <i className="fas fa-envelope me-3"></i>
                                            info@example.com
                                        </p>
                                        <p><i className="fas fa-phone me-3"></i> + 01 234 567 88</p>
                                        <p><i className="fas fa-print me-3"></i> + 01 234 567 89</p>
                                    </div>
                                    <div className="text-center p-4 footerss">
                                        © 2022 Created BY:
                                        <a className="text-reset fw-bold" href="#">
                                            ITCA TEAM</a>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>

                </footer>
            </div>

        );

    }
}


Home.propTypes = {};

export default connect(
    ({app: {categorys}}) =>
        ({categorys}))
(Home);
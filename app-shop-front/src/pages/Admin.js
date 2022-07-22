import React from 'react';
import "./css/Admin.css";
import "./js/AdminStyle";

class Home extends React.Component {

    render() {

        return (
            <body className="body">
            <div>
                <div className="materialContainer">


                    <div className="box">

                        <div className="title">LOGIN</div>

                        <div className="input">
                            <input type="text" name="name" id="name"/>
                            <span className="spin"></span>
                        </div>

                        <div className="input">
                            <input type="password" name="pass" id="pass"/>
                            <span className="spin"></span>
                        </div>

                        <div className="button login">
                            <button><span>GO</span> <i className="fa fa-check"></i></button>
                        </div>
                    </div>

                    <div className="overbox">
                        <div className="material-button alt-2"><span className="shape"></span></div>

                        <div className="title">REGISTER</div>

                        <div className="input">
                            <label htmlFor="regname">Username</label>
                            <input type="text" name="regname" id="regname"/>
                                <span className="spin"></span>
                        </div>

                        <div className="input">
                            <label htmlFor="regpass">Password</label>
                            <input type="password" name="regpass" id="regpass"/>
                                <span className="spin"></span>
                        </div>

                        <div className="input">
                            <label htmlFor="reregpass">Repeat Password</label>
                            <input type="password" name="reregpass" id="reregpass"/>
                                <span className="spin"></span>
                        </div>

                        <div className="button">
                            <button><span>NEXT</span></button>
                        </div>


                    </div>

                </div>
            </div>
            </body>

    );

    }
    }

    Home.propTypes =
        {
        }
    ;

    export default Home;
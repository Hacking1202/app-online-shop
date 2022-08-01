import {Provider} from "react-redux";
import store from "../redux";
import {HashRouter, Route, Routes} from "react-router-dom";
import PublicRoute from "../utils/PublicRoute";
import Home from "../pages/Home";
import NotFound from "../pages/NotFound";
import Category from "../pages/Category";
import Maker from "../pages/Maker";
import Admin from "../pages/Admin";
import Warehouse from "../pages/Warehouse";
import TimeMode from "../pages/TimeMode";

function App() {
  return (
      <Provider store={store}>

        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/admin/' element={<Admin/>}/>
          <Route path='/admin/category' element={<Category/>}/>
          <Route path='/admin/maker' element={<Maker/>}/>
          <Route path='*' element={<NotFound/>}/>
            <Route path='/warehouse' element={<Warehouse/>}/>
            <Route path='/timeMode' element={<TimeMode/>}/>
        </Routes>
      </Provider>
  );
}

export default App;

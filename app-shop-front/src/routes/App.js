import {Provider} from "react-redux";
import store from "../redux";
import {HashRouter, Route, Routes} from "react-router-dom";
import PublicRoute from "../utils/PublicRoute";
import Home from "../pages/Home";
import NotFound from "../pages/NotFound";
import Category from "../pages/Category";
import Maker from "../pages/Maker";
import Admin from "../pages/Admin";

function App() {
  return (
      <Provider store={store}>

        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/admin/' element={<Admin/>}/>
          <Route path='/admin/category' element={<Category/>}/>
          <Route path='/admin/maker' element={<Maker/>}/>
          <Route path='*' element={<NotFound/>}/>

        </Routes>
      </Provider>
  );
}

export default App;

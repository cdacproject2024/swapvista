import { Routes, Route } from "react-router-dom"
import Header from "./NavbarComponent/Header"
import AdminRegisterForm from "./UserComponent/AdminRegisterForm"
import UserLoginForm from "./UserComponent/UserLoginForm"
import UserRegister from "./UserComponent/UserRegister"
import AboutUs from "./PageComponent/AboutUs"
import ContactUs from "./PageComponent/ContactUs"
import HomePage from "./PageComponent/HomePage"
import AddCategoryForm from "./CategoryComponent/AddCategoryForm"
import AddProductForm from "./ProductComponent/AddProductForm"
import Product from "./ProductComponent/Product"
import SellerProducts from "./ProductComponent/SellerProducts"
import ViewSellerProducts from "./ProductComponent/ViewSellerProducts"
import UpdateProductForm from "./ProductComponent/UpdateProductForm"
import ViewAllCategories from "./CategoryComponent/ViewAllCategories"
import UpdateCategoryForm from "./CategoryComponent/UpdateCategoryForm"
import ViewAllProducts from "./ProductComponent/ViewAllProducts"
import ViewAllOrders from "./OrderComponent/ViewAllOrders"
import ViewAllCustomers from "./UserComponent/ViewAllCustomers"
import MyBids from "./ProductOfferComponent/MyBids"
import MyWallet from "./UserComponent/MyWallet"

function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/user/admin/register" element={<AdminRegisterForm />} />
        <Route path="/user/login" element={<UserLoginForm />} />
        <Route path="/user/customer/register" element={<UserRegister />} />
        <Route path="/user/seller/register" element={<UserRegister />} />
        <Route path="/aboutus" element={<AboutUs />} />
        <Route path="/contactus" element={<ContactUs />} />
        <Route path="/product/category/:categoryId/:categoryName" element={<HomePage />} />
        <Route path="/category/add" element={<AddCategoryForm />} />
        <Route path="/product/add" element={<AddProductForm />} />
        <Route path="/product/:productId/category/:categoryId" element={<Product />} />
        <Route path="/product/seller/:sellerId/:sellerName" element={<SellerProducts />} />
        <Route
          path="/product/seller/:sellerId/:sellerName/category/:categoryId/:categoryName"
          element={<SellerProducts />}
        />
        <Route path="/seller/product/all" element={<ViewSellerProducts />} />
        <Route path="/seller/product/update" element={<UpdateProductForm />} />
        <Route path="/admin/category/all" element={<ViewAllCategories />} />
        <Route path="/admin/category/update" element={<UpdateCategoryForm />} />
        <Route path="/admin/product/all" element={<ViewAllProducts />} />
        <Route path="/admin/order/all" element={<ViewAllOrders />} />
        <Route path="/admin/customer/all" element={<ViewAllCustomers />} />
        <Route path="/customer/bid/all" element={<MyBids />} />
        <Route path="/customer/wallet" element={<MyWallet />} />
      </Routes>
    </div>
  )
}

export default App

import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const HeaderUser = () => {
  let navigate = useNavigate();

  const userLogout = () => {
    toast.success("logged out!!!", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    sessionStorage.removeItem("active-customer");
    sessionStorage.removeItem("customer-jwtToken");
    setTimeout(() => {
      navigate("/home");
      window.location.reload(true);
    }, 2000); // Redirect after 3 seconds
  };

  return (
    <ul class="navbar-nav ms-auto mb-2 mb-lg-0 me-5">
    
      <li class="nav-item">
        <Link to="/product/add" class="nav-link active" aria-current="page">
          <b className="text-color">Add Product</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/seller/product/all"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">My Products</b>
        </Link>
      </li>
      <li class="nav-item">
        <Link
          to="/customer/bid/all"
          class="nav-link active"
          aria-current="page"
        >
          <b className="text-color">My Bids</b>
        </Link>
      </li>


      <li class="nav-item">
        <Link to="/customer/wallet" class="nav-link active" aria-current="page">
          <b className="text-color">My Wallet</b>
        </Link>
      </li>

      <li class="nav-item">
        <Link
          to=""
          class="nav-link active"
          aria-current="page"
          onClick={userLogout}
        >
          <b className="text-color">Logout</b>
        </Link>
        <ToastContainer />
      </li>
    </ul>
  );
};

export default HeaderUser;

import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const ViewSellerProducts = () => {
  const seller = JSON.parse(sessionStorage.getItem("active-customer"));

  const seller_jwtToken = sessionStorage.getItem("customer-jwtToken");

  const statusOptions = ["Available", "Deactivated", "Sold", "UnSold"];

  const [allProducts, setAllProducts] = useState([]);

  const [status, setStatus] = useState("");

  const [tempSearchStatus, setTempSearchStatus] = useState("");

  let navigate = useNavigate();

  useEffect(() => {
    const getAllProducts = async () => {
      const allProducts = await retrieveAllProducts();
      if (allProducts) {
        setAllProducts(allProducts.products);
      }
    };

    getAllProducts();
  }, [status]);

  const retrieveAllProducts = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/product/fetch/seller-wise?sellerId=" +
        seller.id +
        "&status=" +
        status
    );
    console.log(response.data);
    return response.data;
  };

  const deleteProduct = (productId, e) => {
    fetch(
      "http://localhost:8080/api/product/delete?productId=" +
        productId +
        "&sellerId=" +
        seller.id,
      {
        method: "DELETE",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + seller_jwtToken,
        },
      }
    )
      .then((result) => {
        result.json().then((res) => {
          if (res.success) {
            toast.success(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });

            setTimeout(() => {
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          } else if (!res.success) {
            toast.error(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setTimeout(() => {
          window.location.reload(true);
        }, 1000); // Redirect after 3 seconds
      });
  };

  const updateProduct = (product) => {
    navigate("/seller/product/update", { state: product });
  };

  const searchProducts = (e) => {
    e.preventDefault();
    setStatus(tempSearchStatus);
  };

  return (
    <div className="mt-3">
      <div
        className="card form-card ms-2 me-2 mb-5 custom-bg shadow-lg"
        style={{
          height: "45rem",
        }}
      >
        <div
          className="card-header custom-bg-text text-center bg-color"
          style={{
            borderRadius: "1em",
            height: "50px",
          }}
        >
          <h2>My Products</h2>
        </div>
        <div
          className="card-body"
          style={{
            overflowY: "auto",
          }}
        >
          <div className="d-flex aligns-items-center justify-content-center mt-1">
            <form class="row">
              <div class="col-auto">
                <b className="text-color">Filter By Status</b>
              </div>
              <div className="col-auto">
                <select
                  name="status"
                  onChange={(e) => setTempSearchStatus(e.target.value)}
                  className="form-control"
                  style={{ width: "300px" }}
                >
                  <option value="">Select Status</option>
                  <option value="">All</option>
                  {statusOptions.map((status) => {
                    return <option value={status}> {status} </option>;
                  })}
                </select>
              </div>
              <div class="col-auto">
                <button
                  type="submit"
                  class="btn bg-color custom-bg-text mb-3"
                  onClick={searchProducts}
                >
                  Search
                </button>
              </div>
            </form>
          </div>

          <div className="table-responsive">
            <table className="table table-hover text-color text-center">
              <thead className="table-bordered border-color bg-color custom-bg-text">
                <tr>
                  <th scope="col">Product</th>
                  <th scope="col">Name</th>
                  <th scope="col">Description</th>
                  <th scope="col">Category</th>
                  <th scope="col">Quantity</th>
                  <th scope="col">Price</th>
                  <th scope="col">Status</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                {allProducts.map((product) => {
                  return (
                    <tr>
                      <td>
                        <img
                          src={
                            "http://localhost:8080/api/product/" +
                            product.image1
                          }
                          class="img-fluid"
                          alt="product_pic"
                          style={{
                            maxWidth: "90px",
                          }}
                        />
                      </td>
                      <td>
                        <b>{product.name}</b>
                      </td>
                      <td>
                        <b>{product.description}</b>
                      </td>
                      <td>
                        <b>{product.category.name}</b>
                      </td>
                      <td>
                        <b>{product.quantity}</b>
                      </td>
                      <td>
                        <b>{product.price}</b>
                      </td>
                      <td>
                        <b>{product.status}</b>
                      </td>
                      <td>
                        {(() => {
                          if (
                            product.status !== "Sold" &&
                            product.status !== "Deactivated"
                          ) {
                            return (
                              <button
                                onClick={() => updateProduct(product)}
                                className="btn btn-sm bg-color custom-bg-text ms-2"
                              >
                                Update
                              </button>
                            );
                          }
                        })()}

                        {(() => {
                          if (product.status === "Available") {
                            return (
                              <button
                                onClick={() => deleteProduct(product.id)}
                                className="btn btn-sm bg-color custom-bg-text ms-2"
                              >
                                Delete
                              </button>
                            );
                          }
                        })()}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewSellerProducts;

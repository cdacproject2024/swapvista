import { useState, useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import ProductCarousel from "./ProductCarousel";

const UpdateProductForm = () => {
  const location = useLocation();
  const product = location.state;

  const [categories, setCategories] = useState([]);

  const seller_jwtToken = sessionStorage.getItem("customer-jwtToken");

  const seller = JSON.parse(sessionStorage.getItem("active-customer"));

  let navigate = useNavigate();

  const retrieveAllCategories = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/category/fetch/all"
    );
    return response.data;
  };

  useEffect(() => {
    const getAllCategories = async () => {
      const resCategory = await retrieveAllCategories();
      if (resCategory) {
        setCategories(resCategory.categories);
      }
    };

    getAllCategories();
  }, []);
  const [endDate, setEndDate] = useState("");

  const [selectedImage1, setSelectImage1] = useState(null);
  const [selectedImage2, setSelectImage2] = useState(null);
  const [selectedImage3, setSelectImage3] = useState(null);

  const [updatedProduct, setUpdatedProduct] = useState({
    id: product.id,
    name: product.name,
    description: product.description,
    price: product.price,
    quantity: product.quantity,
    categoryId: product.categoryId,
    sellerId: product.sellerId,
    endDate: product.endDate,
  });

  const handleInput = (e) => {
    setUpdatedProduct({ ...updatedProduct, [e.target.name]: e.target.value });
  };

  const saveProduct = (e) => {
    e.preventDefault();
    if (seller === null) {
      toast.error("Seller Id is missing!!!", {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      return;
    }

    let updatedEndDateInMillis;

    if (endDate !== "") {
      updatedEndDateInMillis = new Date(endDate).getTime();

      // Check if the conversion is a valid number
      if (isNaN(updatedEndDateInMillis)) {
        toast.error("Please select a valid expiration time.", {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        return;
      }

      if (updatedEndDateInMillis < product.endDate) {
        toast.error("Expiration time cannot be reduced!!!", {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });

        return;
      }

      updatedProduct.endDate = updatedEndDateInMillis;
    }

    fetch("http://localhost:8080/api/product/update/detail", {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + seller_jwtToken,
      },
      body: JSON.stringify(updatedProduct),
    })
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
              navigate("/seller/product/all");
            }, 2000); // Redirect after 3 seconds
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
              navigate("/seller/product/all");
            }, 2000); // Redirect after 3 seconds
          } else {
            toast.error("It Seems Server is down!!!", {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              navigate("/seller/product/all");
            }, 2000); // Redirect after 3 seconds
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
    e.preventDefault();
  };

  const updateProductImage = (e) => {
    e.preventDefault();
    if (seller === null) {
      toast.error("Seller Id is missing!!!", {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      return;
    }

    const formData = new FormData();
    formData.append("image1", selectedImage1);
    formData.append("image2", selectedImage2);
    formData.append("image3", selectedImage3);
    formData.append("id", product.id);

    axios
      .put("http://localhost:8080/api/product/update/image", formData, {
        headers: {
          Authorization: "Bearer " + seller_jwtToken, // Replace with your actual JWT token
        },
      })
      .then((resp) => {
        let response = resp.data;

        if (response.success) {
          toast.success(response.responseMessage, {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
          });

          setTimeout(() => {
            navigate("/seller/product/all");
          }, 2000); // Redirect after 3 seconds
        } else if (!response.success) {
          toast.error(response.responseMessage, {
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
          }, 2000); // Redirect after 3 seconds
        } else {
          toast.error("It Seems Server is down!!!", {
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
          }, 2000); // Redirect after 3 seconds
        }
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
        }, 2000); // Redirect after 3 seconds
      });
  };

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  return (
    <div className="container-fluid">
      <div class="row">
        <div class="col-sm-3 mt-2">
          <div class="card form-card shadow-lg custom-bg">
            <ProductCarousel
              item={{
                image1: product.image1,
                image2: product.image2,
                image3: product.image3,
              }}
            />
          </div>
        </div>
        <div class="col-sm-6 mt-2">
          <div class="card form-card shadow-lg custom-bg">
            <div className="container-fluid">
              <div
                className="card-header bg-color custom-bg-text mt-2 text-center"
                style={{
                  borderRadius: "1em",
                  height: "38px",
                }}
              >
                <h5 class="card-title">Update Product Details</h5>
              </div>
              <div class="card-body text-color">
                <div className="text-center text-color">
                  <b>
                    Product End Date:{" "}
                    <span className="text-danger">
                      {" "}
                      {formatDateFromEpoch(product.endDate)}
                    </span>
                  </b>
                </div>

                <form className="row g-3 mt-1">
                  <div className="col-md-6 mb-3">
                    <label for="title" class="form-label">
                      <b>Product Title</b>
                    </label>
                    <input
                      type="text"
                      class="form-control"
                      id="title"
                      name="name"
                      onChange={handleInput}
                      value={updatedProduct.name}
                    />
                  </div>
                  <div className="col-md-6 mb-3">
                    <label for="description" class="form-label">
                      <b>Product Description</b>
                    </label>
                    <textarea
                      class="form-control"
                      id="description"
                      name="description"
                      rows="3"
                      onChange={handleInput}
                      value={updatedProduct.description}
                    />
                  </div>

                  <div className="col-md-6 mb-3">
                    <label className="form-label">
                      <b>Category</b>
                    </label>

                    <select
                      name="categoryId"
                      onChange={handleInput}
                      className="form-control"
                    >
                      <option value="">Select Category</option>

                      {categories.map((category) => {
                        return (
                          <option value={category.id}> {category.name} </option>
                        );
                      })}
                    </select>
                  </div>

                  <div className="col-md-6 mb-3">
                    <label for="quantity" class="form-label">
                      <b>Product Quantity</b>
                    </label>
                    <input
                      type="number"
                      class="form-control"
                      id="quantity"
                      name="quantity"
                      onChange={handleInput}
                      value={updatedProduct.quantity}
                    />
                  </div>

                  <div className="col-md-6 mb-3">
                    <label for="price" class="form-label">
                      <b>Product Price</b>
                    </label>
                    <input
                      type="number"
                      class="form-control"
                      id="price"
                      name="price"
                      onChange={handleInput}
                      value={updatedProduct.price}
                      readOnly
                    />
                  </div>

                  <div className="col-md-6 mb-3 text-color">
                    <label htmlFor="title" className="form-label">
                      <b>Select Expiry Time</b>
                    </label>
                    <input
                      type="datetime-local"
                      className="form-control"
                      value={endDate}
                      onChange={(e) => setEndDate(e.target.value)}
                    />
                  </div>

                  <div className="d-flex aligns-items-center justify-content-center mb-2">
                    <button
                      type="submit"
                      class="btn bg-color custom-bg-text"
                      onClick={saveProduct}
                    >
                      Update Product
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-3 mt-2">
          <div class="card form-card custom-bg shadow-lg">
            <div className="container-fluid">
              <div
                className="card-header bg-color custom-bg-text mt-2 text-center"
                style={{
                  borderRadius: "1em",
                  height: "38px",
                }}
              >
                <h5 class="card-title">Update Product Image</h5>
              </div>
              <div class="card-body text-color">
                <form className="row">
                  <div className="mb-3">
                    <label for="formFile" class="form-label">
                      <b> Select 1st Image</b>
                    </label>
                    <input
                      class="form-control"
                      type="file"
                      id="formFile"
                      name="image1"
                      onChange={(e) => setSelectImage1(e.target.files[0])}
                    />
                  </div>

                  <div className="mb-3">
                    <label for="formFile" class="form-label">
                      <b> Select 2nd Image</b>
                    </label>
                    <input
                      class="form-control"
                      type="file"
                      id="formFile"
                      name="image2"
                      onChange={(e) => setSelectImage2(e.target.files[0])}
                    />
                  </div>

                  <div className="mb-3">
                    <label for="formFile" class="form-label">
                      <b> Select 3rd Image</b>
                    </label>
                    <input
                      class="form-control"
                      type="file"
                      id="formFile"
                      name="image3"
                      onChange={(e) => setSelectImage3(e.target.files[0])}
                    />
                  </div>

                  <div className="d-flex aligns-items-center justify-content-center mb-2">
                    <button
                      type="submit"
                      class="btn bg-color custom-bg-text"
                      onClick={updateProductImage}
                    >
                      Update Image
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateProductForm;

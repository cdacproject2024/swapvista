import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import ProductCard from "../ProductComponent/ProductCard";
import { useLocation } from "react-router-dom";

const SellerProducts = () => {
  const location = useLocation();
  const seller = location.state;

  const { categoryId, categoryName, sellerName } = useParams();
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        let response;

        if (categoryId == null) {
          // Fetch all products
          response = await axios.get(
            `http://localhost:8080/api/product/fetch/seller-wise?sellerId=${seller.id}&status=Available`
          );
        } else {
          // Fetch products by category
          response = await axios.get(
            `http://localhost:8080/api/product/fetch/seller-wise/category-wise?sellerId=${seller.id}&categoryId=${categoryId}&status=Available`
          );
        }
        if (response.data) {
          setProducts(response.data.products);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [seller, categoryId]);

  return (
    <div className="container-fluid mb-2">
      {/* <Carousel /> */}

      <div
        className="bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
        style={{
          borderRadius: "1em",
          height: "38px",
        }}
      >
        <h5 class="card-title ms-3">Seller Name: {sellerName}</h5>
      </div>

      <div className="col-md-12 mt-3">
        <div className="row row-cols-1 row-cols-md-4 g-4">
          {products.map((product) => {
            return <ProductCard item={product} key={product.id} />;
          })}
        </div>
      </div>
    </div>
  );
};

export default SellerProducts;

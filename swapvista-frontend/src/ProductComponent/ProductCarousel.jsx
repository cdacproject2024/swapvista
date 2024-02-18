const ProductCarousel = (product) => {
  return (
    <div
      id="carouselExampleCaptions2"
      className="carousel slide"
      data-bs-ride="false"
    >
      <div className="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions2"
          data-bs-slide-to="0"
          className="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions2"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions2"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img
            src={"http://localhost:8080/api/product/" + product.item.image1}
            className="d-block card-img-top img-fluid"
            alt="..."
            style={{
              maxHeight: "450px", // Adjust the maximum height as needed
              width: "auto",
              margin: "0 auto",
            }}
          />
        </div>
        <div className="carousel-item">
          <img
            src={"http://localhost:8080/api/product/" + product.item.image2}
            className="d-block card-img-top img-fluid"
            alt="..."
            style={{
              maxHeight: "450px", // Adjust the maximum height as needed
              width: "auto",
              margin: "0 auto",
            }}
          />
        </div>
        <div className="carousel-item">
          <img
            src={"http://localhost:8080/api/product/" + product.item.image3}
            className="d-block card-img-top img-fluid"
            alt="..."
            style={{
              maxHeight: "450px", // Adjust the maximum height as needed
              width: "auto",
              margin: "0 auto",
            }}
          />
        </div>
      </div>
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleCaptions2"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleCaptions2"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default ProductCarousel;

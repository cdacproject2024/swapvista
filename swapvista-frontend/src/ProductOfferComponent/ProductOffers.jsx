import { useEffect, useState } from "react"
import axios from "axios"
import { useParams } from "react-router-dom"

const ProductOffers = () => {
  const [offers, setoffers] = useState([])

  const admin = JSON.parse(sessionStorage.getItem("active-admin"))

  const { productId } = useParams()

  const retrieveAllOffers = async () => {
    const response = await axios.get("http://localhost:8080/api/product/offer/fetch/product?productId=" + productId)
    return response.data
  }

  useEffect(() => {
    const getAllOffers = async () => {
      const alloffers = await retrieveAllOffers()
      if (alloffers) {
        setoffers(alloffers.offers)
      }
    }

    getAllOffers()
  }, [])

  return (
    <div
      class="list-group form-card border-color"
      style={{
        height: "31rem",
      }}>
      <div class="list-group-item list-group-item-action bg-color custom-bg-text text-center">
        <b>Product Bids</b>
      </div>
      <div
        style={{
          overflowY: "auto",
        }}>
        {offers.map((offer) => {
          return (
            <div class="list-group-item list-group-item-action text-color custom-bg">
              <b className="text-color1">
                {(() => {
                  if (offer.product.status === "Sold") {
                    if (offer.status === "Won") {
                      return (
                        <div>
                          {admin ? offer.user.firstName + " " + offer.user.lastName : "Anonymous User"}{" "}
                          <span className="text-success">[WON]</span>
                        </div>
                      )
                    }
                  }
                  return <div>Anonymous User</div>
                })()}
              </b>
              <p> &#8377; {offer.amount}</p>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default ProductOffers

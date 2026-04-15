def calculate_fare(distance_km, rate_per_km, hour):
    total_fare = distance_km * rate_per_km
    if hour >= 17 and hour <= 20:
        total_fare = total_fare * 1.5
    return total_fare

def display_receipt(customer_name, distance_km, vehicle_type, rate_per_km, hour):
    print("---------- Price Receipt ----------")
    print("Customer Name:", customer_name)
    print("Distance Travelled:", distance_km, "km")
    print("Vehicle Type:", vehicle_type)
    print("Ride Hour:", hour)

    final_fare = calculate_fare(distance_km, rate_per_km, hour)
    if hour >= 17 and hour <= 20:
        print("Surge Fee 1.5X applied: YES")
    else:
        print("Surge Fee 1.5X applied : NO")
    print("Total Fare: ₹", final_fare)

    print("Thank you for choosing our service!")

vehicle_rates = {'ECONOMY': 10,'PREMIUM': 18,'SUV': 25}

print("Enter your full name:")
customer_name = input()

print("Enter your mobile number:")
mobile_number = input()

print("Enter hour of the day (0-23):")
while True:
    ride_hour = int(input())
    if 0 <= ride_hour <= 23:
        break
    else:
        print("Ride hour must be between 0 and 23. Try again:")

print("Enter distance in kilometers:")
while True:
    distance_km = int(input())
    if distance_km > 0:
        break
    else:
        print("Distance must be greater than 0. Try again:")

print("Available Vehicles:", vehicle_rates)
print("Enter vehicle type:")
vehicle_type = input()
vehicle_type = vehicle_type.upper()

if vehicle_type not in vehicle_rates:
    print("Service not available")
else:
    display_receipt(customer_name, distance_km, vehicle_type, vehicle_rates[vehicle_type], ride_hour)
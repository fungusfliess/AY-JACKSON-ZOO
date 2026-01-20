==================================================
Zoo Simulation – User Instruction Manual
==================================================

1. Overview
--------------------------------------------------
This zoo simulation allows users to manage animals, habitats, visitors,
and structures while respecting environmental constraints.

Animals can only survive and thrive when placed in suitable habitats that
match their living conditions and flexibility thresholds. Structures and
visitors must also follow strict validation rules to ensure the zoo
functions correctly.


2. Animal Placement Rules
--------------------------------------------------

2.1 Living Conditions
Every animal has a LivingCondition object that represents the environment
it requires to live comfortably. Habitats also contain a LivingCondition.

When placing an animal into a habitat, the program:
- Compares the animal’s LivingCondition with the habitat’s LivingCondition
- Calculates a similarity score
- Checks if the score meets or exceeds the animal’s flexibility value

If the conditions do not meet the flexibility requirement, the animal
cannot be placed in that habitat.


2.2 Flexibility
Flexibility is a decimal value between 0.0 and 1.0 that represents how
tolerant an animal is to imperfect living conditions.

Flexibility ranges:
- 0.9 – 1.0 : Very strict, requires near-perfect conditions
- 0.6 – 0.8 : Moderately flexible
- 0.3 – 0.5 : Very adaptable


3. Animal Living Conditions
--------------------------------------------------

3.1 Mammals

Capybara
  Living Condition Type: LandCondition
  Requirements:
  Temperature: 26
  Humidity: 75
  Region: Wet Grassland
  Soil Compaction: 30
  Slope: 2
  HasWater: true
  Vegetation: 80
  Amount Strutures: 5
  Required Area: 30
  Flexibility: 0.7 Medium
  Type Foods: Grass, Vegetables

Unicorn
  Living Condition Type: LandCondition
  Requirements:
  Temperature: 18
  Humidity: 60
  Region: Enchanted Forest
  Soil Compaction: 45
  Slope: 4
  HasWater: true
  Vegetation: 70
  Amount Strutures: 8
  Required Area: 50
  Flexibility: 0.4 Low
  Type Foods: Grass, Hay, Fruits
  

3.2 Birds

Eagle
  Living Condition Type: LandCondition
  Requirements:
  Temperature: 15
  Humidity: 55
  Region: Mountainous
  Soil Compaction: 85
  Slope: 25
  HasWater: true
  Vegetation: 30
  Amount Strutes: 12
  Required Area: 30
  Flexibility: 0.4 Low
  Type Foods: Meat, Fish

Cockatoo
  Living Condition Type: LandCondition
  Requirements:
  Temperature: 24
  Humidity: 65
  Region: Tropical Forest
  Soil Compaction: 50
  Slope: 5
  HasWater: true
  Vegetation: 80
  Amount Strutes: 15
  Required Area: 15
  Flexibility: 0.7 Medium
  Type Foods: Seeds, Fruits


3.3 Fish

Shark
  Living Condition Type: WaterCondition
  Requirements:
  Temperature: 18
  Humidity: 75
  Region: Ocean
  Acidity: 8.1
  Hardness 180
  Water Temperature: 20
  Has Land: false
  Salinity: 35
  Required Area: 150
  Flexibility: 0.4 Low
  Type Foods: Meat, Fish

Sunfish
  Living Condition Type: WaterCondition
  Requirements:
  Temperature: 2
  Humidity: 70
  Region: Pelagic Zone
  Acidity: 8
  Hardness 160
  Water Temperature: 23
  Has Land: false
  Salinity: 34
  Required Area: 80
  Flexibility: 0.7 Medium
  Type Foods: Algae, Flakes


3.4 Reptles

Snake
  Living Condition Type: LandCondition
  Requirements:
  Temperature: 26
  Humidity: 50
  Region: Grassland
  Soil Compaction: 40
  Slope: 6
  HasWater: true
  Vegetation: 45
  Amount Strutes: 6
  Required Area: 20
  Flexibility: 0.9 High
  Type Foods: Rats, Birds

Crocodile
  Living Condition Type: WaterCondition
  Requirements:
  Temperature: 28
  Humidity: 80
  Region: River Delta
  Acidity: 7.2
  Hardness 180
  Water Temperature: 26
  Has Land: true
  Salinity: 0.5
  Required Area: 50
  Flexibility: 0.4 Low
  Type Foods: Meat, Fish


3.5 Amphibians

Frog
  Living Condition Type: WaterCondition
  Requirements:
  Temperature: 20
  Humidity: 85
  Region: Wetland
  Acidity: 6.8
  Hardness 60
  Water Temperature: 18
  Has Land: true
  Salinity: 0.1
  Required Area: 10
  Flexibility: 0.4 Low
  Type Foods: Flies, Worms

Axolotl
  Living Condition Type: WaterCondition
  Requirements:
  Temperature: 16
  Humidity: 70
  Region: Freshwater
  Acidity: 7.4
  Hardness 150
  Water Temperature: 15
  Has Land: true
  Salinity: 0
  Required Area: 8
  Flexibility: 0.7 Medium
  Type Foods: Insects, Small Fish



4. Habitat Suitability Logic
--------------------------------------------------
An animal is considered suitable for a habitat if:
- The habitat has enough physical space
- The habitat climate matches the animal’s LivingCondition
- The similarity score is greater than or equal to the animal’s flexibility

If any of these conditions fail, the animal cannot be placed in the habitat.


5. Structure Building Rules
--------------------------------------------------

5.1 Rectangular Structures
Examples:
- Restaurant
- Gift Shop

How to build:
- Input two coordinates representing opposite corners of the rectangle

Rules:
- The entire rectangular area must be empty
- If any obstacle exists within the area, the structure will not be built


5.2 Maze Structures

How to build:
- Input the top-left corner only

Rules:
- Maze size is fixed
- Width and height do not need to be provided
- If the fixed maze area overlaps an obstacle, construction fails


5.3 Blob-Based Structures
Examples:
- Pavilions
- Enclosures
- Parks

How to build:
- Input a maximum radius

Rules:
- The system uses a blob generation method
- The blob expands organically within the radius
- The structure can wrap around existing buildings
- Obstacles are automatically avoided
- This allows for more natural and realistic layouts


6. Visitor Management
--------------------------------------------------

6.1 Adding Visitors
When adding a visitor to the zoo, the system enforces a strict validation
process to ensure consistency.

The process is as follows:
1. The system first asks for the visitor’s role
2. The system then asks for the visitor’s age

The visitor will only be added if the age matches the allowed range for
the selected role.


6.2 Visitor Role and Age Validation
Each visitor role has a valid age range. If the entered age does not fall
within the allowed range for the chosen role, the visitor will not be added.

Examples:
- Child: age must fall within the child age range
- Adult: age must fall within the adult age range
- Senior: age must fall within the senior age range

If the age does not match the role, the system rejects the visitor.


6.3 Validation Summary
--------------------------------------------------
- Role must be selected before age
- Age must match the role’s allowed range
- Invalid role-age combinations are rejected
- Only valid visitors are added to the zoo

==================================================
End of Instruction Manual
==================================================

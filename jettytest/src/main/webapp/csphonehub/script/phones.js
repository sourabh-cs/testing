var json = `{
  "brands": [
    {
      "name": "Apple",
      "products": [
        {
          "name": "iPhone",
          "devices": [
            {
              "name": "iPhone 6s",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/apple-iphone-6s1.jpg",
              "specs": {
                "OS": "iOS 9, upgradable to iOS 9.2",
                "Chipset": "Apple A9",
                "CPU": "Dual-core 1.84 GHz Twister",
				"Dimensions": "138.3 x 67.1 x 7.1 mm (5.44 x 2.64 x 0.28 in)",
				"Weight": "143 g (5.04 oz)",
				"Memory": "16/64/128 GB, 2 GB RAM"
              }
            },
            {
              "name": "iPhone 6",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/apple-iphone-6-4.jpg",
              "specs": {
                "OS": "iOS 8, upgradable to iOS 9.2",
                "Chipset": "Apple A8",
                "CPU": "Dual-core 1.4 GHz Typhoon (ARM v8-based)",
				"Dimensions": "138.1 x 67 x 6.9 mm (5.44 x 2.64 x 0.27 in)",
				"Weight": "129 g (4.55 oz)",
				"Memory": "16/64/128 GB, 1 GB RAM DDR3"
              }
            },
            {
              "name": "iPhone 5s",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/apple-iphone-5s-ofic.jpg",
              "specs": {
                "OS": "iOS 7, upgradable to iOS 9.2",
                "Chipset": "Apple A7",
                "CPU": "Dual-core 1.3 GHz Cyclone (ARM v8-based)",
				"Dimensions": "123.8 x 58.6 x 7.6 mm (4.87 x 2.31 x 0.30 in)",
				"Weight": "112 g (3.95 oz)",
				"Memory": "16/32/64 GB, 1 GB RAM DDR3"
              }
            }
          ]
        },
        {
          "name": "iPad",
          "devices": [
            {
              "name": "iPad Pro",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/apple-ipad-pro-.jpg",
              "specs": {
                "OS": "iOS 9, upgradable to iOS 9.2",
                "Chipset": "Apple A9X",
                "CPU": "Dual-core 2.26 GHz (Twister)",
				"Dimensions": "305.7 x 220.6 x 6.9 mm (12.04 x 8.69 x 0.27 in)",
				"Weight": "713 g (Wi-Fi) / 723 g (LTE) (1.57 lb)",
				"Memory": "32/128 GB, 4 GB RAM"
              }
            },
            {
              "name": "iPad mini 4",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/ipad-mini-41.jpg",
              "specs": {
                "OS": "iOS 9, upgradable to iOS 9.2",
                "Chipset": "Apple A8",
                "CPU": "Dual-core 1.5 GHz Typhoon",
				"Dimensions": "203.2 x 134.8 x 6.1 mm (8.0 x 5.31 x 0.24 in)",
				"Weight": "299 g (Wi-Fi) / 304 g (3G/LTE) (10.55 oz)",
				"Memory": "16/64/128 GB, 2 GB RAM"
              }
            },
            {
              "name": "iPad Air",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/ipad-mini-41.jpg",
              "specs": {
                "OS": "iOS 9, upgradable to iOS 9.2",
                "Chipset": "Apple A8",
                "CPU": "Dual-core 1.5 GHz Typhoon",
				"Dimensions": "203.2 x 134.8 x 6.1 mm (8.0 x 5.31 x 0.24 in)",
				"Weight": "299 g (Wi-Fi) / 304 g (3G/LTE) (10.55 oz)",
				"Memory": "16/64/128 GB, 2 GB RAM"
              }
            }
          ]
        }
      ]
    },
    {
      "name": "Samsung",
      "products": [
        {
          "name": "Galaxy",
          "devices": [
            {
              "name": "Galaxy A9",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-a9-2016-.jpg",
              "specs": {
                "OS": "Android OS, v5.1.1 (Lollipop), planned upgrade to v6.0 (Marshmallow)",
                "Chipset": "Qualcomm MSM8976 Snapdragon 652",
                "CPU": "Quad-core 1.8 GHz Cortex-A72 & quad-core 1.2 GHz Cortex-A53",
				"Dimensions": "161.7 x 80.9 x 7.4 mm (6.37 x 3.19 x 0.29 in)",
				"Weight": "200 g (7.05 oz)",
				"Memory": "32 GB, 3 GB RAM"
              }
            },
            {
              "name": "Galaxy A5",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-a5-2016.jpg",
              "specs": {
                "OS": "Android OS, v5.1.1 (Lollipop)",
                "Chipset": "Qualcomm MSM8939 Snapdragon 615 Exynos 7580 Octa",
                "CPU": "Quad-core 1.2 GHz Cortex-A53 & Quad-core 1.5 GHz Cortex-A53 Octa-core 1.6 GHz Cortex-A53",
				"Dimensions": "144.8 x 71 x 7.3 mm (5.70 x 2.80 x 0.29 in)",
				"Weight": "155 g (5.47 oz)",
				"Memory": "16 GB, 2 GB RAM"
              }
            }
          ]
        },
        {
          "name": "Galaxy Tab",
          "devices": [
            {
              "name": "Galaxy A9",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-tab-e-80.jpg",
              "specs": {
                "OS": "Android OS, v5.1.1 (Lollipop)",
                "Chipset": "Qualcomm MSM8916 Snapdragon 410",
                "CPU": "Quad-core 1.3 GHz Cortex-A53",
				"Dimensions": "212.1 x 126.1 x 8.9 mm (8.35 x 4.96 x 0.35 in)",
				"Weight": "360 g (12.70 oz)",
				"Memory": "16 GB, 1.5 GB RAM"
              }
            }
          ]
        }
      ]
    },
    {
      "name": "Motorola",
      "products": [
        {
          "name": "Moto G",
          "devices": [
            {
              "name": "Motorola Moto G (3rd gen)",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/motorola-moto-g-3rd-gen-r.jpg",
              "specs": {
                "OS": "Android OS, v5.1.1 (Lollipop), upgradable to v6.0 (Marshmallow)",
                "Chipset": "Qualcomm MSM8916 Snapdragon 410",
                "CPU": "Quad-core 1.4 GHz Cortex-A53",
				"Dimensions": "142.1 x 72.4 x 11.6 mm (5.59 x 2.85 x 0.46 in)",
				"Weight": "155 g (5.47 oz)",
				"Memory": "8/16 GB, 1/2 GB RAM"
              }
            }
          ]
        },
        {
          "name": "Moto X",
          "devices": [
            {
              "name": "Moto X Force",
			  "imageurl": "http://cdn2.gsmarena.com/vv/bigpic/motorola-moto-x-force1.jpg",
              "specs": {
                "OS": "Android OS, v5.1.1 (Lollipop), planned upgrade to v6.0 (Marshmallow)",
                "Chipset": "Qualcomm MSM8994 Snapdragon 810",
                "CPU": "Quad-core 1.5 GHz Cortex-A53 & Quad-core 2.0 GHz Cortex-A57",
				"Dimensions": "149.8 x 78 x 9.2 mm (5.90 x 3.07 x 0.36 in)",
				"Weight": "169 g (5.96 oz)",
				"Memory": "32/64 GB, 3 GB RAM"
              }
            }
          ]
        }
      ]
    }
  ]
}`;
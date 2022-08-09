import requests
import bs4
import time

def recipeCollector():
    
    # Extract recipe URLs
    recipe_urls = []
    ingredient_list = ['avocado', 'chocolate','kale', 'tofu', 'quinoa', 'blueberries', 'mushrooms', 'zucchini']
    for ingredient in ingredient_list:
        base_url = f"https://simple-veganista.com/tag/{ingredient}/"
        response = requests.get(base_url)
        soup = bs4.BeautifulSoup(response.text, 'html.parser')
        entry_soup = soup.find_all('h2', class_='entry-title')
        for content in entry_soup:
            recipe_urls.append(content.find_all('a')[0].attrs['href'])
        
    print("URLs Collected!")
    print(recipe_urls)
    print("================================")
    time.sleep(10)
    
    # Get recipe information from each URL
    counter = 0
    for recipe_url in recipe_urls:
        response = requests.get(recipe_url)
        soup = bs4.BeautifulSoup(response.text, 'html.parser')
        
        # Get information from the URL. 
        result = []
        # Name
        name_soup = soup.find_all('h2', class_='tasty-recipes-title')
        name_content = name_soup[0].get_text()
        result.append(name_content)
        
        # Detailed Ingredients
        ingredient_details_soup = soup.find_all('div', class_ = 'tasty-recipes-ingredients-body')
        if (ingredient_details_soup != []): 
            ingredients_details_content = ingredient_details_soup[0].get_text().strip("\n")
        else:
            ingredients_details_content = "N/A"
        result.append(ingredients_details_content)
        
        # Ingredients
        if (ingredient_details_soup != []): 
            ingredients_content = ingredient_details_soup[0].find_all('strong')
            if (ingredients_content != []):
                ingredients = ''
                for tag in ingredients_content:
                    ingredients += tag.get_text()
                    ingredients += ', '
            else:
                ingredients = "N/A"
        else:
            ingredients = "N/A"
        result.append(ingredients.strip(', '))
        
        # Instructions
        instructions_soup = soup.find_all('div', class_ = 'tasty-recipes-instructions-body')
        if (instructions_soup != []):
            instructions_content = instructions_soup[0].get_text()
        else:
            instructions_content = "N/A"
        result.append(instructions_content)
        
        # Yield
        yield_soup = soup.find_all('li', class_='yield')
        if (yield_soup != []): 
            yield_content = yield_soup[0].get_text().strip('Yield: ')
            
        else:
            yield_content = "N/A"
        result.append(yield_content)
        
        # Cook time
        cooktime_soup = soup.find_all('li', class_='total-time')
        if (cooktime_soup != []): 
            cooktime_content = cooktime_soup[0].get_text()
        else:
            cooktime_content = "N/A"
        result.append(cooktime_content)
        
        # Collect all the information
        print_result = "***Recipe***\n"
        print_result += result[0] + '///\n'
        print_result += result[2] + '///\n'
        print_result += result[1] + '///\n'
        print_result += result[3] + '///\n'
        print_result += result[4] + '///\n'
        print_result += result[5] + '///\n'
        print_result += "******\n"
    
        # Write to the file
        fp = open('RecipeDatabase.txt', 'a')
        fp.write(print_result)
        fp.close()
        
        counter += 1
        print(f"Recipe {counter} Collected!")
        time.sleep(10)
    

recipeCollector()
import random
import names


first = (
    "Super", "Retarded", "Great", "Sexy", "Vegan", "Brave", "Shy", "Cool", "Poor", "Rich", "Fast", "Gummy", "Yummy",
    "Masked", "Unusual", "American", "Bisexual", "MLG", "Mlg", "lil", "Lil")
second = (
    "Coder", "Vegan", "Man", "Hacker", "Horse", "Bear", "Goat", "Goblin", "Learner", "Killer", "Woman", "Programmer",
    "Spy",
    "Stalker", "Spooderman", "Carrot", "Goat", "Quickscoper", "Quickscoper")

t1 = ("Backdoor",
      "Backseat",
      "Big Titty",
      "Blonde",
      "Booty",
      "Campus",
      "Chocolate",
      "Chubby",
      "Deep Throat",
      "Dildo",
      "Dirty",
      "Doggystyle",
      "Dorm Room",
      "Double Chocolate",
      "Drunk",
      "Eager",
      "Enema",
      "Funky",
      "Gang Bang",
      "Geriatric",
      "Goth",
      "Greasy",
      "Greedy",
      "Hairy",
      "Hidden Camera",
      "Hot",
      "Itty Bitty",
      "Indecent",
      "Latin",
      "Little White",
      "Lolita",
      "Mature",
      "Mega",
      "Messy",
      "MILF",
      "Older",
      "Orthodox",
      "Perverse",
      "GILF",
      "Private",
      "Secret",
      "Shaving",
      "Silicone",
      "Sluts,",
      "Sloppy",
      "Super-Gay",
      "Tattooed",
      "Teenage",
      "Thirsty",
      "Tiny",
      "Ugly",
      "Unprotected",
      "Virgin",
      "Voluptuous",
      "Well Used",
      "XXX",
      "Young",
      "Neighborhood",
      "Secret",
      "Private",
      "Lumpy",
      "Chunky",
      "Squealing",
      "Moaning",
      "Forbidden",
      "Time for"
      , "Lets do"
      , "Cum Explosion,")

t2 = ("Affair",
      "Asians",
      "Asphyxiation",
      "Babysitter",
      "Ball Action",
      "Ballsacks",
      "Bar Sluts",
      "Bikini",
      "Boob Hunters",
      "Bondage",
      "Butts",
      "Co-Eds",
      "Facials",
      "Fantasies",
      "Fetish Freaks",
      "Gang Bang",
      "Girls Next Door",
      "Hooters",
      "House",
      "Housewives",
      "Jizz-Gobblers",
      "Lingerie",
      "Maniacs",
      "Meat",
      "Moresomes",
      "Nuns",
      "Nymphos",
      "Orgasm Chasers",
      "Patrol",
      "Penetration",
      "Perverts",
      "Pigs",
      "Russian",
      "Schoolgirls",
      "Sluts",
      "Snatch-Snatchers",
      "Stallions",
      "Strippers",
      "Studs",
      "Ta-Ta's",
      "Thong",
      "Threesomes",
      "Thrust",
      "Tomboys",
      "Tramps",
      "Whores",
      "Sister In-Law",
      "Moms",
      "Dongs",
      "Bones",
      "Diddling",
      "Getting Drunk ",
      "Drinking",
      "Fingering Things Out",
      "Trump",
      "Hillary",
      "Squid Massacre",
      "Olga",
      "Svetlana",
      "Heidi",
      "Siv Jensen",
      "Erna",
      "Japanese",
      "Fabio"
      )

t3 = (": HENTAI EDITION",
      "Trump wants revenge for Bengasi!",
      ": 21",
      ": 5",
      ": 6",
      ": 3",
      "Neckbeard Fantasy",
      ": 6",
      ": 15",
      ": 12",
      ": 13",
      ": 3",
      ": 2",
    ": 23",
    ": 2, the Wrath of Kahn"
    , ": 6"
    , ": 8"
    , "and a Sandwich"
    , "and Backdoor Love"
    , "and the Backdoor Betties"
    , "and the Bangers of Gangs"
    , "and the Busty Amazons"
    , "and the Give-It-Up Girls from Mars"
    , "Upside Down"
    , "and the Gobble Ball Girls"
    , "and the Green Monkey"
    , "and the Head Crew"
    , "and the Mistress of Spankville"
    , "and the Sacks of Balls"
    , "in Pink"
    , "and the Scat-Farmers of Katmandu"
    , "with Braces"
    , "in Black Lace"
    , "and the Spermicidal Maniacs"
    , "and the Vagina Frighteners"
    , "at the Nail-Me Palace"
    , "at the Poon Palace"
    , "Boinking Madly"
    , "Get Horney"
    , "do Bangkok"
    , "in the Shower"
    , "at Bathtime"
    , "2Girls1Cup Ode"
    , "Exposed"
    , "Extravaganza"
    , "from Space"
    , "Go Camping"
    , "in the Ghetto"
    , "in 3D"
    , "in Hand"
    , "in Little Hoo-Hoo"
    , "in a Tight Spot"
    , "of Kings Landing"
    , "on Sex Mountain"
    , "on Spring Break"
    , "in Pigtails"
    , "on the Yellow River"
    , "with Laser Nipples"
    , "of the East Block"
    , "All Naked"
    , "Galactic Conquests"
    , "Rock Hard"
    , "Spreading"
    , "Spread Eagle"
    , "BUKKAKI SPECIAL"
    , "vs. the Penis Snatchers"
    , "who Hump Like Bunnies"
    , "with a Firm Grip"
    , "at Ram Ranch"
    , "with a Rash"
    , "with Animal Love"
    , "with Dicks"
    , "with Hairy Nuts"
    , "with Handcuffs"
    , "in Stockings"
    , "with Housewives"
    , "with Older Women"
    , "with Penis Hands"
    , "with Twin Action"
    , "of Canada"
    , "in Rio"
    , "and the Potato Farmers"
    , "Hunt Jizm"
    , "Experimenting"
    , "Becoming a Woman"
    , "at the Raunch Ranch"
    , "on the Farm"
    , "with Openings Available"
    , "with Grandma"
    , "for Fitness",
    "Done Right",
    "Narnia Adventures",
    ": Now with Bonus Cock",
    "and Guzzling Man-Chowder",)

t4 = (" Homevideos",
      " Productions",
      " Studios",
      " Films",
      " Entertainment",
      " Animations",
      " Pictures")

genre = ("ACTION", "SCFI", "DRAMA", "HISTORY" )
size = int(input("How many movies?:"))
fileName = input("File name:")

fil = open(fileName+".txt", "w+")
y = str(size)+"\n"
fil.write(y)

for x in range(size):
    producent = names.get_full_name(gender='male')
    tittel = random.choice(t1) + " " + random.choice(t2)+" "+random.choice(t3)
    if(len(tittel) >=40):
        tittel = random.choice(t1) + " " + random.choice(t2)+" : "+str(random.randint(2, 33))
    year = str(random.randint(1960, 2020))
    gen = random.choice(genre)

    if(random.getrandbits(1)):
        company = producent+random.choice(t4)
    else:
        company = random.choice(first) +random.choice(t4)
    lineString = str(x+1)+"#"+producent+"#"+tittel+"#"+year+"#"+gen+"#"+company+"\n"
    fil.write(lineString)

fil.close()
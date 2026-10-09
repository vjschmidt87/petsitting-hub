package com.portfolio.petsittinghub.config;

import com.portfolio.petsittinghub.entity.CareTip;
import com.portfolio.petsittinghub.entity.User;
import com.portfolio.petsittinghub.enums.AnimalType;
import com.portfolio.petsittinghub.enums.CareTipCategory;
import com.portfolio.petsittinghub.enums.Difficulty;
import com.portfolio.petsittinghub.repository.CareTipRepository;
import com.portfolio.petsittinghub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CareTipRepository careTipRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.username}")
    private String seedUsername;

    @Value("${app.seed.password}")
    private String seedPassword;

    @Value("${app.seed.email}")
    private String seedEmail;

    public DataSeeder(UserRepository userRepository, CareTipRepository careTipRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.careTipRepository = careTipRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        User admin = new User();
        admin.setUsername(seedUsername);
        admin.setEmail(seedEmail);
        admin.setPassword(passwordEncoder.encode(seedPassword));
        admin = userRepository.save(admin);

        // DOG tips
        saveTip(admin, AnimalType.DOG, CareTipCategory.FEEDING, Difficulty.BEGINNER,
                "Balanced Diet for Dogs",
                "Dieta Equilibrada para Cães",
                "A well-balanced diet is essential for your dog's health and longevity. Adult dogs typically need a mix of high-quality protein (chicken, beef, or fish), complex carbohydrates (brown rice, sweet potato), healthy fats, and fiber. Feed adult dogs twice daily — morning and evening — and always provide fresh water. Avoid giving chocolate, grapes, onions, garlic, and xylitol, which are toxic to dogs. The amount varies by breed, size, and activity level. Consult your vet for portions specific to your dog.",
                "Uma dieta equilibrada é essencial para a saúde e longevidade do seu cão. Cães adultos geralmente precisam de uma mistura de proteína de alta qualidade (frango, carne bovina ou peixe), carboidratos complexos (arroz integral, batata-doce), gorduras saudáveis e fibras. Alimente cães adultos duas vezes ao dia — manhã e noite — e sempre forneça água fresca. Evite dar chocolate, uvas, cebola, alho e xilitol, que são tóxicos para cães. A quantidade varia por raça, tamanho e nível de atividade. Consulte seu veterinário para porções específicas.",
                "Learn the basics of feeding your dog a healthy, balanced diet.",
                "Aprenda os fundamentos de alimentar seu cão com uma dieta saudável e equilibrada.");

        saveTip(admin, AnimalType.DOG, CareTipCategory.GROOMING, Difficulty.INTERMEDIATE,
                "Brushing and Bathing Your Dog",
                "Escovação e Banho do seu Cão",
                "Regular brushing removes loose fur, prevents matting, and distributes natural oils for a healthy coat. Short-haired breeds need brushing once a week, while long-haired breeds may need daily sessions. Use the right brush type: slicker brushes for tangles, bristle brushes for short coats, and undercoat rakes for double-coated breeds. Bathe your dog every 4–6 weeks using dog-specific shampoo — human shampoo disrupts their skin pH. Always dry thoroughly, especially in skin folds, to prevent bacterial infections.",
                "A escovação regular remove pelos soltos, previne nós e distribui os óleos naturais para um pelo saudável. Raças de pelo curto precisam de escovação uma vez por semana, enquanto raças de pelo longo podem precisar de sessões diárias. Use o tipo correto de escova: escovas de arame para emaranhados, escovas de cerdas para pelos curtos e rastelos para raças com subpelo duplo. Dê banho no seu cão a cada 4–6 semanas usando shampoo específico para cães — shampoo humano altera o pH da pele deles. Sempre seque bem, especialmente nas dobras da pele, para prevenir infecções bacterianas.",
                "Master regular grooming routines to keep your dog's coat healthy.",
                "Domine rotinas regulares de higiene para manter o pelo do seu cão saudável.");

        saveTip(admin, AnimalType.DOG, CareTipCategory.HEALTH, Difficulty.BEGINNER,
                "Essential Vaccinations for Dogs",
                "Vacinações Essenciais para Cães",
                "Vaccinations protect your dog from serious and potentially fatal diseases. Core vaccines include rabies, distemper, parvovirus, and adenovirus. Puppies typically start their vaccination schedule at 6–8 weeks of age, with boosters every 3–4 weeks until 16 weeks old. Adult dogs need annual or triennial boosters depending on the vaccine. Non-core vaccines like Bordetella (kennel cough) and Lyme disease may be recommended based on lifestyle and location. Keep a vaccination record and follow your veterinarian's schedule.",
                "As vacinações protegem seu cão de doenças graves e potencialmente fatais. As vacinas essenciais incluem raiva, cinomose, parvovirose e adenovírus. Filhotes geralmente iniciam o calendário de vacinação com 6–8 semanas de idade, com reforços a cada 3–4 semanas até as 16 semanas. Cães adultos precisam de reforços anuais ou trienais dependendo da vacina. Vacinas não essenciais como Bordetella (tosse dos canis) e Doença de Lyme podem ser recomendadas com base no estilo de vida e localização. Mantenha um registro de vacinação e siga o calendário do seu veterinário.",
                "Understand the essential vaccines every dog needs and when to give them.",
                "Entenda as vacinas essenciais que todo cão precisa e quando administrá-las.");

        saveTip(admin, AnimalType.DOG, CareTipCategory.TRAINING, Difficulty.BEGINNER,
                "Basic Obedience Training",
                "Treinamento Básico de Obediência",
                "Start training early — puppies can learn basic commands from 8 weeks old. Use positive reinforcement: reward good behavior with treats, praise, or play. Teach one command at a time: sit, stay, come, down, and leave it. Keep sessions short (5–10 minutes) and consistent. Use the same word and hand signal each time. Never punish mistakes — redirect and reward the correct behavior instead. Practice in different environments once your dog masters a command at home. Patience and consistency are more effective than any tool or technique.",
                "Comece o treinamento cedo — filhotes podem aprender comandos básicos a partir de 8 semanas. Use reforço positivo: recompense bom comportamento com petiscos, elogios ou brincadeiras. Ensine um comando de cada vez: senta, fica, vem, deita e larga. Mantenha as sessões curtas (5–10 minutos) e consistentes. Use a mesma palavra e sinal de mão cada vez. Nunca puna erros — redirecione e recompense o comportamento correto. Pratique em diferentes ambientes quando o cão dominar um comando em casa. Paciência e consistência são mais eficazes que qualquer ferramenta ou técnica.",
                "Learn the fundamentals of teaching your dog basic commands.",
                "Aprenda os fundamentos de ensinar comandos básicos ao seu cão.");

        // CAT tips
        saveTip(admin, AnimalType.CAT, CareTipCategory.GROOMING, Difficulty.BEGINNER,
                "Brushing Your Cat's Coat",
                "Escovando o Pelo do seu Gato",
                "Regular brushing reduces shedding, prevents hairballs, and strengthens the bond with your cat. Short-haired cats benefit from weekly brushing with a soft bristle brush, while long-haired breeds like Persians need daily grooming with a wide-toothed comb followed by a slicker brush. Always brush in the direction of hair growth. Pay special attention to areas prone to matting: behind ears, under the chin, and the belly. If you encounter mats, use a mat splitter instead of scissors. Most cats enjoy gentle brushing once they get accustomed to it.",
                "A escovação regular reduz a queda de pelos, previne bolas de pelo e fortalece o vínculo com seu gato. Gatos de pelo curto se beneficiam de escovação semanal com escova de cerdas macias, enquanto raças de pelo longo como Persas precisam de escovação diária com pente de dentes largos seguido de escova de arame. Sempre escove na direção do crescimento do pelo. Preste atenção especial a áreas propensas a emaranhamento: atrás das orelhas, sob o queixo e na barriga. Se encontrar nós, use um desembaraçador em vez de tesoura. A maioria dos gatos gosta de escovação suave quando se acostuma.",
                "Keep your cat's coat healthy and reduce hairballs with proper brushing.",
                "Mantenha o pelo do seu gato saudável e reduza bolas de pelo com escovação adequada.");

        saveTip(admin, AnimalType.CAT, CareTipCategory.HOUSING, Difficulty.BEGINNER,
                "Setting Up the Perfect Litter Box",
                "Montando a Caixa de Areia Perfeita",
                "The litter box is crucial to your cat's wellbeing. The golden rule: one litter box per cat, plus one extra. Place boxes in quiet, accessible locations away from food and water. The box should be 1.5 times your cat's body length. Use unscented clumping litter filled 2–3 inches deep — most cats prefer this texture. Scoop daily and fully change the litter weekly. Wash the box monthly with mild soap (avoid strong chemicals). If your cat starts avoiding the box, rule out medical issues first, then evaluate the box's cleanliness, location, and litter type.",
                "A caixa de areia é crucial para o bem-estar do seu gato. A regra de ouro: uma caixa por gato, mais uma extra. Coloque as caixas em locais tranquilos e acessíveis, longe de comida e água. A caixa deve ter 1,5 vezes o comprimento do corpo do gato. Use areia aglomerante sem perfume com 5–7 cm de profundidade — a maioria dos gatos prefere essa textura. Limpe diariamente e troque toda a areia semanalmente. Lave a caixa mensalmente com sabão neutro (evite produtos químicos fortes). Se seu gato começar a evitar a caixa, descarte problemas médicos primeiro, depois avalie a limpeza, localização e tipo de areia.",
                "Set up a litter box that keeps your cat happy and your home clean.",
                "Monte uma caixa de areia que mantenha seu gato feliz e sua casa limpa.");

        saveTip(admin, AnimalType.CAT, CareTipCategory.BEHAVIOR, Difficulty.INTERMEDIATE,
                "Understanding Cat Body Language",
                "Entendendo a Linguagem Corporal dos Gatos",
                "Cats communicate primarily through body language. A slowly blinking cat is showing trust and affection — blink back slowly to reciprocate. An upright tail with a curved tip signals a happy, confident cat. Flattened ears and a puffed-up tail indicate fear or aggression — give the cat space. A twitching tail tip means your cat is focused or mildly irritated. Kneading with paws is a comfort behavior from kittenhood. Belly exposure can mean trust, but many cats dislike belly touches — it's often a trap. Understanding these signals helps prevent bites and strengthens your relationship.",
                "Gatos se comunicam principalmente através de linguagem corporal. Um gato piscando lentamente está mostrando confiança e afeto — pisque de volta lentamente para retribuir. Uma cauda ereta com ponta curvada indica um gato feliz e confiante. Orelhas achatadas e cauda eriçada indicam medo ou agressão — dê espaço ao gato. Uma ponta da cauda tremendo significa que o gato está focado ou levemente irritado. Amassar com as patas é um comportamento de conforto desde filhote. Exposição da barriga pode significar confiança, mas muitos gatos não gostam de toques na barriga — geralmente é uma armadilha. Entender esses sinais ajuda a prevenir mordidas e fortalece seu relacionamento.",
                "Decode your cat's signals to build a stronger bond.",
                "Decodifique os sinais do seu gato para construir um vínculo mais forte.");

        // BIRD tips
        saveTip(admin, AnimalType.BIRD, CareTipCategory.HOUSING, Difficulty.BEGINNER,
                "Setting Up a Bird Cage",
                "Montando uma Gaiola para Aves",
                "The cage is your bird's primary living space, so size matters. As a minimum, your bird should be able to fully extend its wings without touching the sides. Bar spacing must match your bird's size — too wide and small birds can escape or get stuck. Place the cage at eye level in a room where the family gathers, but away from the kitchen (cooking fumes are toxic to birds), direct sunlight, and drafts. Include multiple perches of varying diameter and texture, food and water dishes, and toys for mental stimulation. Line the bottom with plain newspaper for easy cleaning.",
                "A gaiola é o espaço principal de vida da sua ave, então o tamanho importa. No mínimo, a ave deve conseguir estender totalmente as asas sem tocar as laterais. O espaçamento das barras deve corresponder ao tamanho da ave — muito largo e aves pequenas podem escapar ou ficar presos. Coloque a gaiola na altura dos olhos em um cômodo onde a família se reúne, mas longe da cozinha (vapores de cozinha são tóxicos para aves), luz solar direta e correntes de ar. Inclua vários poleiros de diâmetros e texturas variados, comedouros e bebedouros, e brinquedos para estimulação mental. Forre o fundo com jornal simples para facilitar a limpeza.",
                "Create a safe and stimulating home for your feathered friend.",
                "Crie um lar seguro e estimulante para seu amigo de penas.");

        saveTip(admin, AnimalType.BIRD, CareTipCategory.FEEDING, Difficulty.INTERMEDIATE,
                "Nutritious Diet for Pet Birds",
                "Dieta Nutritiva para Aves Domésticas",
                "A seed-only diet is one of the biggest mistakes bird owners make — it's like feeding a child only french fries. Seeds are high in fat and low in essential nutrients. A healthy bird diet should consist of formulated pellets (60–70%), fresh vegetables and fruits (20–30%), and seeds as occasional treats (5–10%). Safe vegetables include broccoli, carrots, dark leafy greens, and bell peppers. Safe fruits include apples (no seeds), berries, and melon. Never feed avocado, chocolate, caffeine, alcohol, or fruit seeds/pits. Provide fresh water daily and wash food dishes thoroughly.",
                "Uma dieta apenas de sementes é um dos maiores erros dos donos de aves — é como alimentar uma criança só com batatas fritas. Sementes são ricas em gordura e pobres em nutrientes essenciais. Uma dieta saudável deve consistir de ração extrusada (60–70%), vegetais e frutas frescos (20–30%) e sementes como petiscos ocasionais (5–10%). Vegetais seguros incluem brócolis, cenouras, folhas verdes escuras e pimentões. Frutas seguras incluem maçãs (sem sementes), frutas vermelhas e melão. Nunca ofereça abacate, chocolate, cafeína, álcool ou sementes/caroços de frutas. Forneça água fresca diariamente e lave os comedouros completamente.",
                "Go beyond seeds to give your bird the nutrition it truly needs.",
                "Vá além das sementes para dar à sua ave a nutrição que ele realmente precisa.");

        saveTip(admin, AnimalType.BIRD, CareTipCategory.HEALTH, Difficulty.ADVANCED,
                "Recognizing Illness in Pet Birds",
                "Reconhecendo Doenças em Aves Domésticas",
                "Birds instinctively hide illness to avoid appearing vulnerable, so by the time symptoms are visible, the condition may be advanced. Watch for: fluffed feathers for extended periods, sitting on the cage floor, changes in droppings (color, consistency, volume), discharge from eyes or nostrils, labored breathing or tail bobbing, loss of appetite, and reduced vocalization. A healthy bird is alert, active, and has clean, smooth feathers. Weigh your bird weekly — sudden weight loss is an early red flag. Keep an avian vet's contact handy, as regular vets may lack bird-specific expertise. Annual checkups are strongly recommended.",
                "Aves instintivamente escondem doenças para não parecerem vulneráveis, então quando os sintomas são visíveis, a condição pode estar avançada. Observe: penas eriçadas por períodos prolongados, ficar no fundo da gaiola, mudanças nas fezes (cor, consistência, volume), secreção nos olhos ou narinas, respiração ofegante ou balanço da cauda, perda de apetite e redução nas vocalizações. Uma ave saudável é alerta, ativa e tem penas limpas e lisas. Pese sua ave semanalmente — perda de peso súbita é um alerta precoce. Tenha o contato de um veterinário especializado em aves, pois veterinários comuns podem não ter experiência específica. Exames anuais são fortemente recomendados.",
                "Learn the subtle signs that your bird may need veterinary attention.",
                "Aprenda os sinais sutis de que sua ave pode precisar de atenção veterinária.");

        // FISH tips
        saveTip(admin, AnimalType.FISH, CareTipCategory.HOUSING, Difficulty.BEGINNER,
                "Setting Up Your First Aquarium",
                "Montando Seu Primeiro Aquário",
                "A successful aquarium starts with proper planning. Choose at least a 20-gallon tank for beginners — larger tanks are more stable and forgiving of mistakes. Essential equipment: a quality filter rated for your tank size, a heater (for tropical fish), a thermometer, substrate (gravel or sand), and a lid to prevent jumpers. Before adding fish, cycle your tank for 4–6 weeks to establish beneficial bacteria that convert toxic ammonia into nitrate. Test water parameters regularly with a liquid test kit (ammonia, nitrite, nitrate, pH). Add fish gradually — no more than 2–3 small fish per week — to avoid overwhelming the biological filter. Place the tank away from direct sunlight to prevent algae blooms.",
                "Um aquário bem-sucedido começa com planejamento adequado. Escolha pelo menos um tanque de 75 litros para iniciantes — tanques maiores são mais estáveis e tolerantes a erros. Equipamentos essenciais: um filtro de qualidade dimensionado para seu tanque, um aquecedor (para peixes tropicais), um termômetro, substrato (cascalho ou areia) e uma tampa para evitar fugas. Antes de adicionar peixes, cicle seu tanque por 4–6 semanas para estabelecer bactérias benéficas que convertem amônia tóxica em nitrato. Teste os parâmetros da água regularmente com kit de teste líquido (amônia, nitrito, nitrato, pH). Adicione peixes gradualmente — não mais que 2–3 peixes pequenos por semana — para não sobrecarregar o filtro biológico. Posicione o tanque longe da luz solar direta para evitar proliferação de algas.",
                "Everything you need to know to set up a healthy freshwater aquarium.",
                "Tudo que você precisa saber para montar um aquário de água doce saudável.");

        saveTip(admin, AnimalType.FISH, CareTipCategory.FEEDING, Difficulty.BEGINNER,
                "Feeding Aquarium Fish Properly",
                "Alimentando Peixes de Aquário Corretamente",
                "Overfeeding is the number one mistake in fishkeeping — it pollutes water, causes algae, and harms fish health. Feed only what your fish can consume in 2–3 minutes, once or twice daily. Remove any uneaten food after feeding. Vary the diet: high-quality flakes or pellets as a staple, supplemented with frozen or freeze-dried bloodworms, brine shrimp, and daphnia. Bottom feeders like corydoras need sinking pellets or wafers. Herbivorous fish benefit from blanched vegetables (zucchini, spinach, peas). Fast your fish one day per week to aid digestion. Match food size to mouth size — small fish need crushed flakes or micro pellets.",
                "Superalimentação é o erro número um na aquariofilia — polui a água, causa algas e prejudica a saúde dos peixes. Alimente apenas o que seus peixes conseguem consumir em 2–3 minutos, uma ou duas vezes ao dia. Remova qualquer alimento não consumido após a alimentação. Varie a dieta: flocos ou pellets de alta qualidade como base, suplementados com bloodworms congelados ou liofilizados, artêmias e dáfnias. Peixes de fundo como corydoras precisam de pellets ou pastilhas que afundam. Peixes herbívoros se beneficiam de vegetais escaldados (abobrinha, espinafre, ervilhas). Faça jejum nos peixes um dia por semana para auxiliar a digestão. Adeque o tamanho do alimento ao tamanho da boca — peixes pequenos precisam de flocos triturados ou micro pellets.",
                "Learn the right way to feed your aquarium fish and avoid common mistakes.",
                "Aprenda a maneira correta de alimentar seus peixes de aquário e evite erros comuns.");

        saveTip(admin, AnimalType.FISH, CareTipCategory.HEALTH, Difficulty.INTERMEDIATE,
                "Maintaining Water Quality",
                "Mantendo a Qualidade da Água",
                "Water quality is the foundation of fish health — most diseases stem from poor water conditions. Perform weekly water changes of 20–30% using a gravel vacuum to remove debris from the substrate. Always treat tap water with a dechlorinator before adding it to the tank. Monitor key parameters: ammonia and nitrite should always read zero, nitrate below 40 ppm, and pH stable for your species. Temperature fluctuations of more than 2°F in a day stress fish. Clean filter media in old tank water (never tap water) to preserve beneficial bacteria. Watch for signs of poor water quality: gasping at the surface, clamped fins, lethargy, loss of color, and white spots (ich). When in doubt, test the water — it's almost always the answer.",
                "A qualidade da água é a base da saúde dos peixes — a maioria das doenças surge de condições inadequadas da água. Realize trocas parciais semanais de 20–30% usando um sifão para remover detritos do substrato. Sempre trate a água da torneira com um desclorificante antes de adicioná-la ao tanque. Monitore parâmetros-chave: amônia e nitrito devem sempre estar em zero, nitrato abaixo de 40 ppm e pH estável para sua espécie. Flutuações de temperatura maiores que 1°C em um dia estressam os peixes. Limpe a mídia do filtro em água velha do tanque (nunca água da torneira) para preservar bactérias benéficas. Observe sinais de má qualidade da água: ofegar na superfície, nadadeiras fechadas, letargia, perda de cor e pontos brancos (íctio). Na dúvida, teste a água — quase sempre é a resposta.",
                "Keep your aquarium water pristine to prevent disease and stress.",
                "Mantenha a água do seu aquário impecável para prevenir doenças e estresse.");

        // LIZARD tips
        saveTip(admin, AnimalType.LIZARD, CareTipCategory.HOUSING, Difficulty.INTERMEDIATE,
                "Creating the Ideal Terrarium",
                "Criando o Terrário Ideal",
                "A proper terrarium replicates your lizard's natural habitat. Research your specific species — a bearded dragon needs a 40-gallon desert setup, while a crested gecko thrives in a tall, humid enclosure. Key elements: appropriate substrate (avoid loose sand for young lizards), a temperature gradient with a warm basking spot and a cooler side, UVB lighting (essential for calcium metabolism in many species), hiding spots on both warm and cool sides, and a water dish. Use thermometers and hygrometers to monitor conditions. Spot-clean daily and deep-clean monthly.",
                "Um terrário adequado replica o habitat natural do seu lagarto. Pesquise sua espécie específica — um dragão barbudo precisa de um terrário desértico de 150 litros, enquanto um gecko crestado prospera em um recinto alto e úmido. Elementos essenciais: substrato apropriado (evite areia solta para lagartos jovens), gradiente de temperatura com ponto quente para banho de sol e lado mais fresco, iluminação UVB (essencial para metabolismo de cálcio em muitas espécies), esconderijos nos lados quente e frio, e pote de água. Use termômetros e higrômetros para monitorar as condições. Faça limpeza rápida diariamente e limpeza profunda mensalmente.",
                "Build the perfect terrarium environment for your reptile.",
                "Construa o ambiente de terrário perfeito para seu réptil.");

        saveTip(admin, AnimalType.LIZARD, CareTipCategory.HEALTH, Difficulty.ADVANCED,
                "Signs of Illness in Lizards",
                "Sinais de Doença em Lagartos",
                "Reptiles are masters at hiding illness. Common health issues include metabolic bone disease (MBD) from insufficient UVB or calcium, respiratory infections from improper humidity or temperature, and parasites. Warning signs: lethargy, loss of appetite, swollen joints or limbs, difficulty walking, mucus around the mouth or nostrils, sunken eyes, discolored skin patches, irregular shedding, and weight loss. MBD may present as rubbery jaw, tremors, or leg swelling. If you notice any of these signs, consult a reptile-specialized veterinarian immediately. Prevention through proper husbandry is always better than treatment.",
                "Répteis são mestres em esconder doenças. Problemas de saúde comuns incluem doença metabólica óssea (DMO) por insuficiência de UVB ou cálcio, infecções respiratórias por umidade ou temperatura inadequadas, e parasitas. Sinais de alerta: letargia, perda de apetite, articulações ou membros inchados, dificuldade para andar, muco ao redor da boca ou narinas, olhos fundos, manchas descoloridas na pele, muda irregular e perda de peso. A DMO pode se apresentar como mandíbula mole, tremores ou inchaço nas pernas. Se notar qualquer desses sinais, consulte um veterinário especializado em répteis imediatamente. Prevenção através de manejo adequado é sempre melhor que tratamento.",
                "Identify common health problems in pet lizards before they become critical.",
                "Identifique problemas de saúde comuns em lagartos domésticos antes que se tornem críticos.");

        saveTip(admin, AnimalType.LIZARD, CareTipCategory.FEEDING, Difficulty.BEGINNER,
                "Feeding Your Lizard Correctly",
                "Alimentando seu Lagarto Corretamente",
                "Lizard diets vary dramatically by species. Bearded dragons are omnivores — juveniles eat mostly insects (80%) while adults shift to mostly vegetables (70%). Leopard geckos are strict insectivores. Green iguanas are herbivores. Always research your specific species. For insectivores, gut-load feeder insects 24 hours before offering them, and dust them with calcium and vitamin D3 powder. Offer variety: crickets, dubia roaches, mealworms, and black soldier fly larvae. For herbivores, provide dark leafy greens (collard, mustard, dandelion), squash, and bell peppers. Avoid iceberg lettuce, spinach in excess, and citrus fruits.",
                "As dietas de lagartos variam dramaticamente por espécie. Dragões barbudos são onívoros — juvenis comem principalmente insetos (80%) enquanto adultos mudam para principalmente vegetais (70%). Geckos-leopardo são insectívoros estritos. Iguanas verdes são herbívoros. Sempre pesquise sua espécie específica. Para insectívoros, alimente os insetos com nutrientes 24 horas antes de oferecê-los, e polvilhe-os com cálcio e vitamina D3. Ofereça variedade: grilos, baratas dubia, tenébrios e larvas de mosca soldado negra. Para herbívoros, forneça folhas verdes escuras (couve, mostarda, dente-de-leão), abóbora e pimentão. Evite alface iceberg, excesso de espinafre e frutas cítricas.",
                "Learn what to feed your lizard based on its species and life stage.",
                "Aprenda o que alimentar seu lagarto com base em sua espécie e estágio de vida.");

        // EXOTIC tips
        saveTip(admin, AnimalType.EXOTIC, CareTipCategory.GENERAL, Difficulty.BEGINNER,
                "Choosing an Exotic Pet",
                "Escolhendo um Pet Exótico",
                "Exotic pets — such as sugar gliders, hedgehogs, ferrets, rabbits, and hermit crabs — require specialized care that differs significantly from cats and dogs. Before acquiring an exotic pet, research: legality in your area, lifespan (some live 20+ years), space requirements, dietary needs, veterinary availability (exotic vets are scarce), social needs (many species need companions), and daily care time. Avoid impulse purchases. Visit reputable breeders or rescue organizations rather than pet stores. Factor in ongoing costs: specialized food, habitat maintenance, and exotic vet bills can be substantial. The right exotic pet for you is one whose needs you can realistically meet for its entire life.",
                "Pets exóticos — como petauros-do-açúcar, ouriços, furões, coelhos e caranguejos-eremitas — requerem cuidados especializados que diferem significativamente de gatos e cães. Antes de adquirir um pet exótico, pesquise: legalidade em sua região, expectativa de vida (alguns vivem mais de 20 anos), requisitos de espaço, necessidades alimentares, disponibilidade veterinária (veterinários de exóticos são escassos), necessidades sociais (muitas espécies precisam de companheiros) e tempo diário de cuidado. Evite compras por impulso. Visite criadores respeitáveis ou organizações de resgate em vez de pet shops. Considere custos contínuos: alimentação especializada, manutenção do habitat e contas veterinárias de exóticos podem ser substanciais. O pet exótico certo para você é aquele cujas necessidades você pode realisticamente atender por toda a vida dele.",
                "What to consider before bringing home an unusual pet.",
                "O que considerar antes de trazer um pet incomum para casa.");

        saveTip(admin, AnimalType.EXOTIC, CareTipCategory.HOUSING, Difficulty.INTERMEDIATE,
                "Housing for Small Exotic Mammals",
                "Alojamento para Pequenos Mamíferos Exóticos",
                "Small exotic mammals like rabbits, ferrets, and guinea pigs need far more space than most pet store cages provide. Rabbits need at minimum a 4x their body length enclosure plus supervised free-roam time. Ferrets need multi-level cages with at least 3–4 hours of daily supervised playtime outside. Guinea pigs need at least 7.5 sq ft for one, 10.5 sq ft for two. For all species: use safe bedding (avoid cedar and pine shavings — their oils cause respiratory problems), provide hiding spots for security, ensure adequate ventilation, and maintain appropriate temperatures. Enrichment items like tunnels, chew toys, and foraging opportunities prevent boredom and associated behavioral problems.",
                "Pequenos mamíferos exóticos como coelhos, furões e porquinhos-da-índia precisam de muito mais espaço do que a maioria das gaiolas de pet shop oferece. Coelhos precisam de um recinto de no mínimo 4 vezes o comprimento do corpo, mais tempo supervisionado de passeio livre. Furões precisam de gaiolas de múltiplos níveis com pelo menos 3–4 horas diárias de brincadeira supervisionada fora. Porquinhos-da-índia precisam de pelo menos 0,7 m² para um, 1 m² para dois. Para todas as espécies: use cama segura (evite aparas de cedro e pinho — seus óleos causam problemas respiratórios), forneça esconderijos para segurança, garanta ventilação adequada e mantenha temperaturas apropriadas. Itens de enriquecimento como túneis, brinquedos de roer e oportunidades de forrageamento previnem tédio e problemas comportamentais associados.",
                "Give your small exotic mammal the space and setup it deserves.",
                "Dê ao seu pequeno mamífero exótico o espaço e a configuração que ele merece.");

        saveTip(admin, AnimalType.EXOTIC, CareTipCategory.HEALTH, Difficulty.ADVANCED,
                "Finding an Exotic Animal Veterinarian",
                "Encontrando um Veterinário de Animais Exóticos",
                "One of the biggest challenges of exotic pet ownership is finding qualified veterinary care. Most veterinary schools provide minimal exotic animal training, so standard vets may not safely treat your pet. Search for veterinarians certified by the Association of Exotic Mammal Veterinarians (AEMV) or the Association of Reptilian and Amphibian Veterinarians (ARAV). Interview potential vets: ask what species they treat regularly, what diagnostic equipment they have, and whether they handle emergencies. Build a relationship with your exotic vet before an emergency arises. Schedule annual wellness exams even when your pet seems healthy — exotic animals hide illness extremely well.",
                "Um dos maiores desafios da posse de pets exóticos é encontrar cuidado veterinário qualificado. A maioria das faculdades de veterinária oferece treinamento mínimo em animais exóticos, então veterinários comuns podem não tratar seu pet com segurança. Procure veterinários certificados pela Associação de Veterinários de Mamíferos Exóticos (AEMV) ou pela Associação de Veterinários de Répteis e Anfíbios (ARAV). Entreviste potenciais veterinários: pergunte quais espécies eles tratam regularmente, que equipamento diagnóstico possuem e se atendem emergências. Construa um relacionamento com seu veterinário de exóticos antes de uma emergência surgir. Agende exames anuais de rotina mesmo quando seu pet parece saudável — animais exóticos escondem doenças extremamente bem.",
                "How to find and choose a qualified vet for your exotic pet.",
                "Como encontrar e escolher um veterinário qualificado para seu pet exótico.");
    }

    private void saveTip(User author, AnimalType animalType, CareTipCategory category,
                         Difficulty difficulty, String title, String titlePt,
                         String content, String contentPt, String summary, String summaryPt) {
        CareTip tip = new CareTip();
        tip.setAuthor(author);
        tip.setAnimalType(animalType);
        tip.setCategory(category);
        tip.setDifficulty(difficulty);
        tip.setTitle(title);
        tip.setTitlePt(titlePt);
        tip.setContent(content);
        tip.setContentPt(contentPt);
        tip.setSummary(summary);
        tip.setSummaryPt(summaryPt);
        careTipRepository.save(tip);
    }
}

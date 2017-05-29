########################################
# Tilastollinen päättely R-ohjelmistolla 
# Kevat 2017 
# Viikko 6
# Malliratkaisut 

#######################################
# Tehtava 1
# a
ot <- read.csv2('OT2014.csv', stringsAsFactors = FALSE)
sarake_nimet <- paste('q1_8', letters[1:7], sep = '')
sarake_nimet
ot_tyytyv <- ot[, sarake_nimet]
# tai subset-funktio
ot_tyytyv <- subset(ot, select = sarake_nimet)

# b
puuttuvat <- apply(ot_tyytyv, 1, function(row) sum(is.na(row)))
table(puuttuvat)

# c
tyytyvaisyys <- rowMeans(ot_tyytyv,na.rm=TRUE)
# tai apply-funktiolla
tyytyvaisyys <- apply(ot_tyytyv, 1, mean, na.rm = TRUE)

# d
tyytyvaisyys[puuttuvat >= 4] <- NA

# e
sum(is.na(tyytyvaisyys))
# saadaan viisi puuttuvaa, kuten pitääkin


#######################################
# Tehtava 2
# a
y <- tyytyvaisyys
y_mean <- mean(y, na.rm = TRUE)
s <- sd(y, na.rm = TRUE )
n <- sum(!is.na(y))
t1 <- (y_mean - 2.2) / (s / sqrt(n)) 
t1

# b
p <- 2 * (1 - pt(abs(t1), df = n - 1))
p

# c
t.test(tyytyvaisyys, mu = 2.2)

# Hyvältä näyttää, t.test antaa samat tulokset
# tosin p-arvo poikkeaa hieman numeerisen 
# epätarkkuuden takia (15:sta desimaalissa, ei mitään merkitystä...).
# Täsmälleen sama p-arvo saataisiin laskemalla:
# 2 * pt(-abs(t), df = n-1)


# p-arvo on 1.4 * 10^-14, eli hyvin pieni, ja erityisesti pienempää kuin etukäteen
# kiinnitetty testin merkitsevyystaso 0.05. Siten nollahypoteesi siitä,
# että tyytyväisyyden odotusarvo olisi 2.2 voidaan hylätä. Voidaan siis päätellä
# että tyytyväisyys-muuttujan arvo poikkeaa 2.2:sta


#######################################
# Tehtava 3
# a
curve(from=-8,to=8,dt(x,df=n-1))
# tai
x <- seq(-8,8, by = .01)
plot(x, dt(x, df = n - 1), type = 'l')

# b
abline(v = qt(.025, df = n-1), col = 'orange')
abline(v = qt(.975, df = n-1), col = 'orange')

# c
abline(v = t1, col = 'violet')

# d
# Testin kriittinen alue on jakauman hännissä t:n jakauman 2.5 prosentin kvantiilin vasemmalla ja
# 97.5 prosentin kvantiilin oikealla puolella. Testisuureen havaittu arvo on siis selkeästi testin
# kriittisellä alueella, eli nollahypoteesi hylätään.


#######################################
# Tehtava 4
# a
t_sim <- rt(100, df = n-1)

# b
abline(v = t_sim, col = 'green')

# c 

# Riippuu simulaation tuloksesta, keskimäärin 5/100 = 0.05 testisuureiden arvoista tulisi sijoittua
# testin kriittiselle alueelle

# d
sum(t_sim < qt(.025, df = n-1) | t_sim > qt(.975, df = n-1))

#######################################
# Tehtava 5
# a
normal_sim <- replicate(100, rnorm(n, 2.2, s))

# b
z <- apply(normal_sim, 2, function(x) (mean(x) - 2.2)  / (s / sqrt(n)))

# c
x <- seq(-8, 8, by = .01)
plot(x, dnorm(x), type = 'l')

# d
abline(v = qnorm(.025), col ='orange')
abline(v = qnorm(.975), col ='orange')

# e
abline(v = z, col = 'green')


# f)
# Kuva näyttää hyvin samanlaiselta kuin edellisessä tehtävässä. Tämä johtuu ensinnäkin siitä, että t:n jakauma
# vapausasteella n-1 = 3614 on käytännössä lähes standardinormaalijakauma, sillä t:n jakauma lähenee
# standardinormaalijakaumaa vapausasteen kasvaessa.

# Käytännössä edellisessä tehtävässä simuloitiin nollahypoteesin mukaisia
# testisuureiden arvoja simuloimalla suoraan t:n jakaumasta,
# kun taas tässä tehtävässä simuloitiin ensin aineisto nollahypoteesin mukaisesta jakaumasta (normaali-
# jakauma, jonka keskiarvo mu = 2.2) ja sitten
# normalisoitiin näiden otosten keskiarvot, jolloin saatiin nollahypoteesin mukaisia testisuureen arvoja.

# Kyseessä oli siis simulointi, joten todellinen keskihajonta tiedettiin ja testisuure voitiin jakaa sillä,
# jolloin se noudattaa standardinormaalijakaumaa. Näin todellisen keskihajonnan estimoimiseen
# ei tarvinnut käyttää otoskeskihajontaa, missä tapauksessa testisuure noudattaa t:n jakaumaa
# standardinormaalijakauman sijaan.


#######################################
# Tehtava 6
# a
t_testi <- function(mean_y, sd_y, mu_0, n) {
  keskivirhe <- sd_y / sqrt(n)
  t <- (mean_y - mu_0) / keskivirhe
  p <- 2 * (1 - pt(t, df = n-1))
  
  t_0.025 <- qt(0.975, df=n-1)
  luottamusvali <- mean_y + c(-t_0.025 * keskivirhe , t_0.025 * keskivirhe)
  tulos <- list(t = t, p = p, luottamusvali_95 = luottamusvali)
  return(tulos)
}

# b
t_testi(y_mean, s, 2.2, n)
t.test(tyytyvaisyys, mu = 2.2)

# hyvältä näyttää...

#######################################
# Tehtava 7
# a 
ot$q5_2 <- factor(ot$q5_2, labels = c('nainen', 'mies'))

# b
x <- tyytyvaisyys[ot$q5_2 == 'nainen']
y <- tyytyvaisyys[ot$q5_2 == 'mies']

var_1 <- var(x, na.rm = TRUE)
var_2 <- var(y, na.rm = TRUE)
n_1 <- sum(!is.na(x))
n_2 <- sum(!is.na(y))

# ALKUPERÄISESSÄ TEHTÄVÄNANNOSSA OLI VIRHE s_p:n KAAVASSA, JOLLA SE OLISI LASKETTU VIRHEELLISESTI:
# s_p <- sqrt(((n_1 - 1) * var_1 + (n_2 - 2) * var_2) / (n_1 + n_2 - 2))
# VASTAUS YLLÄOLEVALLA KAAVALLA TULKITAAN MYÖS OIKEAKSI, KOSKA VIRHE OLI TEHTÄVÄNANNOSSA

s_p <- sqrt(((n_1 - 1) * var_1 + (n_2 - 1) * var_2) / (n_1 + n_2 - 2))
s_p

# c
mean_1 <- mean(x, na.rm = TRUE)
mean_2 <- mean(y, na.rm = TRUE)

t_testisuure <- (mean_1 - mean_2) / (s_p * sqrt(1 / n_1 + 1 / n_2))   

# d 
df <- n_1 + n_2 - 2
p <- 2 * (1 - pt(abs(t_testisuure), df = df))

# e
t_testisuure
p
t.test(tyytyvaisyys ~ ot$q5_2, var.equal = TRUE)

# jes! oikein laskettu...

##################################
# Tehtävä 8
uusi_taulu <- matrix(c(120,80,140,60), nrow=2)
chisq.test(uusi_taulu, corr=F)

# Nyt khiin neliön testin testisuureen arvo on 4.40, ja p-arvo 0.036,
# kun vastaavat arvot olivat kurssimonisteen esimerkissä 35.84 ja 2.14 * 10^-9.
# Muodollisesti se, hylkääkö testi nollahypoteesin siitä, ettei hiivalaatujen
# välillä ole eroja riippuu nyt etukäteen valitusta merkitsevyystasosta.
# Jos olisi käytetty merkitsevyystasoa 0.05, nollahypoteesi hylättäisiin,
# mutta merkitsevyystasoa 0.01 käytettäessä se jäisi voimaan.

# Joka tapauksessa tämä aineisto todistaa huomattavasti heikommin 
# hiivalaatujen erojen puolesta, kuin monisteen esimerkin aineisto,
# jonka perusteella voidaan käytännössä varmasti päätellä, että
# hiivalaatujen välillä on eroja. Joten ehkä leipurin kannattaa tämän
# perusteella epäillä, että hänelle toimitettu hiiva todellakin on B-laatuista,
# mutta kuitenkin kerätä vielä suurempi aineisto jatkamalla testailua,
# ennen kuin päättää vaihtaa hiivan toimittajaa?



##################################
# Tehtävä 9
myynti <- matrix(c(73,94-73, 71, 86-71, 61, 74-61), nrow=2)
chisq.test(myynti)

# p-arvoksi saadaan 0.64, eli nollahypoteesiä siitä, että eri väristen 
# leipien menekki olisi yhtäläistä, ei tämän testin perusteella ole
# perusteita hylätä. Siten esimerkkimme leipurilla ei ole 
# syytä epäillä värin vaikuttavan myyntiin.





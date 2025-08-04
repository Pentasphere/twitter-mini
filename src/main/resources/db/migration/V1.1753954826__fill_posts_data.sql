insert into post(twitter_user_id, topic, text, tags)
select id,
       'First Day at Work',
       'Started My new JobToday. So exited about opportunities there.',
       'work, career, newbeginnings'
from twitter_user
where login = 'vadim'
union all
select id, 'Weekend Plans', 'Going hiking this weekend. Anyone wants to join?', 'hiking, outdoor, weekend'
from twitter_user
where login = 'vadim'
union all
select id, 'Tech Review', 'Just got the latest smartphone. Will share my thoughts seen?', 'tech, review, gadgets'
from twitter_user
where login = 'vadim';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Study Goals',
       'Preparing for my final exams. Wish me lucky.',
       'study, education, exams'
from twitter_user
where login = 'aziret'
union all
select id, 'Gaming Night', 'Posting & Gaming tournament this Friday!', 'gaming, esports, vpn'
from twitter_user
where login = 'aziret'
union all
select id, 'Gaming Progress', 'Finally All perfect', 'progress, finally, perfect'
from twitter_user
where login = 'aziret';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Life mind',
       'Knowing about life. Be humble.',
       'philosophy, life, mind'
from twitter_user
where login = 'aidar'
union all
select id, 'Life is good', 'Be positive', 'be, somebody, good'
from twitter_user
where login = 'aidar'
union all
select id, 'Still live', 'I am alive', 'alive, still, world'
from twitter_user
where login = 'aidar';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Beauty is tool',
       'Make a makeup and be perfect',
       'makeup, tool, beauty'
from twitter_user
where login = 'aisanat'
union all
select id, 'Beauty competition', 'Making photo every Day!', 'photo, competition, beauty'
from twitter_user
where login = 'aisanat'
union all
select id, 'Amazing life', 'I am present perfect', 'perfect, present, amazing'
from twitter_user
where login = 'aisanat';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Future begins',
       'Live this life for a long time.',
       'future, life, time'
from twitter_user
where login = 'tilek'
union all
select id, 'Long day', 'Doing nothing!', 'long, nothing, something'
from twitter_user
where login = 'tilek'
union all
select id, 'Short day', 'Do a lot!', 'do, short, day'
from twitter_user
where login = 'tilek';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'I am kyrgyz',
       'I want to be a hero',
       'kyrgyz, hero, be'
from twitter_user
where login = 'danil'
union all
select id, 'Being kyrgyz', 'Participating in tusho toy!', 'toy, kyrgyz, tusho'
from twitter_user
where login = 'danil'
union all
select id, 'Keeping being kyrgyz', 'I have become kyrgyz', 'kyrgyz, international, interkyrgyz'
from twitter_user
where login = 'danil';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Study Goals',
       'Preparing for my final exams. Wish me lucky.',
       'study, education, exams'
from twitter_user
where login = 'google'
union all
select id, 'Gaming Night', 'Posting & Gaming tournament this Friday!', 'gaming, esports, vpn'
from twitter_user
where login = 'google'
union all
select id, 'Gaming Progress', 'Finally All perfect', 'progress, finally, perfect'
from twitter_user
where login = 'google';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'I am kyrgyz',
       'I want to be a hero',
       'kyrgyz, hero, be'
from twitter_user
where login = 'microsoft'
union all
select id, 'Being kyrgyz', 'Participating in tusho toy!', 'toy, kyrgyz, tusho'
from twitter_user
where login = 'microsoft'
union all
select id, 'Keeping being kyrgyz', 'I have become kyrgyz', 'kyrgyz, international, interkyrgyz'
from twitter_user
where login = 'microsoft';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Future begins',
       'Live this life for a long time.',
       'future, life, time'
from twitter_user
where login = 'apple'
union all
select id, 'Long day', 'Doing nothing!', 'long, nothing, something'
from twitter_user
where login = 'apple'
union all
select id, 'Short day', 'Do a lot!', 'do, short, day'
from twitter_user
where login = 'apple';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Beauty is tool',
       'Make a makeup and be perfect',
       'makeup, tool, beauty'
from twitter_user
where login = 'tesla'
union all
select id, 'Beauty competition', 'Making photo every Day!', 'photo, competition, beauty'
from twitter_user
where login = 'tesla'
union all
select id, 'Amazing life', 'I am present perfect', 'perfect, present, amazing'
from twitter_user
where login = 'tesla';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'Life mind',
       'Knowing about life. Be humble.',
       'philosophy, life, mind'
from twitter_user
where login = 'amazon'
union all
select id, 'Life is good', 'Be positive', 'be, somebody, good'
from twitter_user
where login = 'amazon'
union all
select id, 'Still live', 'I am alive', 'alive, still, world'
from twitter_user
where login = 'amazon';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'First Day at Work',
       'Started My new JobToday. So exited about opportunities there.',
       'work, career, newbeginnings'
from twitter_user
where login = 'meta'
union all
select id, 'Weekend Plans', 'Going hiking this weekend. Anyone wants to join?', 'hiking, outdoor, weekend'
from twitter_user
where login = 'meta'
union all
select id, 'Tech Review', 'Just got the latest smartphone. Will share my thoughts seen?', 'tech, review, gadgets'
from twitter_user
where login = 'meta';

insert into post(twitter_user_id, topic, text, tags)
select id,
       'First Day at Work',
       'Started My new JobToday. So exited about opportunities there.',
       'work, career, newbeginnings'
from twitter_user
where login = 'netflix'
union all
select id, 'Weekend Plans', 'Going hiking this weekend. Anyone wants to join?', 'hiking, outdoor, weekend'
from twitter_user
where login = 'netflix'
union all
select id, 'Tech Review', 'Just got the latest smartphone. Will share my thoughts seen?', 'tech, review, gadgets'
from twitter_user
where login = 'netflix';
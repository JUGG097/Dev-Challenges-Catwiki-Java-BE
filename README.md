# Catwiki Java/SpringBoot Backend Project (The backend for the Catwiki Website deployed [here](https://catwiki-adeoluwa.netlify.app/))

This project was developed using `Java` v "17" and `Spring Boot` v "2.7.3".

Deployed on a `Digital Oceans` Droplet using `Github Actions` for CI/CD.

The Catwiki Website was deployed with `Netlify` link [here](https://catwiki-adeoluwa.netlify.app/).

Figma design was provided by [devChallenges.io](https://devchallenges.io/).

You can clone project and customise at your end.

### API Documentation

- 'http://127.0.0.1:8080/api/v1/topten' Endpoint

METHOD: 'GET'

SUCCESS RESPONSE (200): {'success': true, 'data': '**********'}

ERROR RESPONSE (4**, 5**): {'success': false, 'message': '***********'}


- 'http://127.0.0.1:8080/api/v1/details/{cat_id}' Endpoint

METHOD: 'GET'

SUCCESS RESPONSE (200): {'success': true, 'data': '**********'}

ERROR RESPONSE (4**, 5**): {'success': false, 'message': '***********'}


- 'http://127.0.0.1:8080/api/v1/photos/{cat_id}' Endpoint

METHOD: 'GET'

SUCCESS RESPONSE (200): {'success': true, 'data': '**********'}

ERROR RESPONSE (4**, 5**): {'success': false, 'message': '***********'}

- 'http://127.0.0.1:8080/api/v1/breedlist' Endpoint

METHOD: 'GET'

SUCCESS RESPONSE (200): {'success': true, 'data': '**********'}

ERROR RESPONSE (4**, 5**): {'success': false, 'message': '***********'}

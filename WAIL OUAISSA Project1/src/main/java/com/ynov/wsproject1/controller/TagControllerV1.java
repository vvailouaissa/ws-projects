package com.ynov.wsproject1.controller;

import com.ynov.wsproject1.service.TagService;
import com.ynov.wsproject1.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import
        jakarta.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


//@CrossOrigin(origins = "http://example.com") // Allow only http://example.com to access this controller
@RestController
@RequestMapping("/api/v1/tags")
public class TagControllerV1 {

    @Autowired
    private TagService tagService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    // @Autowired
    // private TagServiceV1 tagServiceV1;

    // @Autowired
    //  private TagServiceV2 tagServiceV2;

    // Example request:x
    // Sorting by Name in Ascending Order:     GET http://localhost:8080/tags?page=0&size=10&sort=name,asc
    // Sorting by Name in Descending Order:    GET http://localhost:8080/api/tags?page=0&size=10&sort=name,desc


    //@CrossOrigin(origins = "http://example.com") // Apply CORS only to this endpoint
    @GetMapping
    public ResponseEntity<Page<Tag>> getTags(@PageableDefault(size = 10) Pageable pageable) {
        Page<Tag> tags = tagService.findAllTags(pageable);
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);
    }


    @GetMapping(path = "/tags/negociable",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getTagsNegociable(Pageable pageable) {
        Page<Tag> tags = tagService.findAllTags(pageable);
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/version")
    public ResponseEntity<?> getTags(@RequestHeader(value = "API-Version", defaultValue = "1") String apiVersion, Pageable pageable) {
        if ("1".equals(apiVersion)) {
            //   return ResponseEntity.ok(tagServiceV1.findAllTags(pageable));
        } else if ("2".equals(apiVersion)) {
            //   return ResponseEntity.ok(tagServiceV2.findAllTags(pageable));
        } else {
            return ResponseEntity.badRequest().body("Unsupported API version: " + apiVersion);
        }
        return null;
    }

    @GetMapping("/cacheable")
    public ResponseEntity<Page<Tag>> getCacheableTags(@PageableDefault(size = 10) Pageable pageable) {
        Page<Tag> tags = tagService.findAllTags(pageable);
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

         long expireTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10);
        HttpHeaders headers = new HttpHeaders();

         headers.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES).getHeaderValue());
        headers.setExpires(expireTime);

        return ResponseEntity.ok().headers(headers).body(tags);
    }



    @GetMapping("/greeting")
    public String greeting(HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);
        String greeting = messageSource.getMessage("greeting", null, locale);

        DateFormat dateFormat = new SimpleDateFormat(messageSource.getMessage("date.format", null, locale));
        String formattedDate = dateFormat.format(new Date());

        return greeting + "! " + formattedDate;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Optional<Tag>>> getTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.findById(id);
        Link selfLink = linkTo(methodOn(TagControllerV1.class).getTagById(id)).withSelfRel();

        Link fakeDataLink = linkTo(methodOn(TagControllerV1.class).getTagFakeData(id)).withRel("fakeData");

        EntityModel<Optional<Tag>> resource = EntityModel.of(tag, selfLink, fakeDataLink);

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{id}/fake")
    public ResponseEntity<String> getTagFakeData(@PathVariable Long id) {
        return ResponseEntity.ok("Fake data for tag " + id);
    }
}

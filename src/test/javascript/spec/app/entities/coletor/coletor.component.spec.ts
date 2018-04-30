/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ColetorComponent } from '../../../../../../main/webapp/app/entities/coletor/coletor.component';
import { ColetorService } from '../../../../../../main/webapp/app/entities/coletor/coletor.service';
import { Coletor } from '../../../../../../main/webapp/app/entities/coletor/coletor.model';

describe('Component Tests', () => {

    describe('Coletor Management Component', () => {
        let comp: ColetorComponent;
        let fixture: ComponentFixture<ColetorComponent>;
        let service: ColetorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ColetorComponent],
                providers: [
                    ColetorService
                ]
            })
            .overrideTemplate(ColetorComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ColetorComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ColetorService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Coletor(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.coletors[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});

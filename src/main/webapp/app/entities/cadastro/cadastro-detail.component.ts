import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Cadastro } from './cadastro.model';
import { CadastroService } from './cadastro.service';

@Component({
    selector: 'jhi-cadastro-detail',
    templateUrl: './cadastro-detail.component.html'
})
export class CadastroDetailComponent implements OnInit, OnDestroy {

    cadastro: Cadastro;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cadastroService: CadastroService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCadastros();
    }

    load(id) {
        this.cadastroService.find(id)
            .subscribe((cadastroResponse: HttpResponse<Cadastro>) => {
                this.cadastro = cadastroResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCadastros() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cadastroListModification',
            (response) => this.load(this.cadastro.id)
        );
    }
}

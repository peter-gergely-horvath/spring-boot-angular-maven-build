export class Command<T> {

    protocol = 'CM/1.0';
    auth: any;

    constructor(
        public command: string,
        public parameters: {}
        ) {}
}
